package br.edu.ifpi.catce.sistemareserva.controller;

import br.edu.ifpi.catce.sistemareserva.model.FuncionarioModel;
import br.edu.ifpi.catce.sistemareserva.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class FuncionarioController {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioController(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @GetMapping("/funcionario")
    public String funcionario(){
        return "Funcionario/Funcionario";
    }

    @GetMapping("/cadastroFuncionario")
    public String cadastroFuncionario(Model model){
        model.addAttribute(new FuncionarioModel());
        return "Funcionario/Cadastrar";
    }

    @PostMapping("/cadastroRealizadoFunc")
    public String cadastroRealizadoFunc(FuncionarioModel funcionarioModel, RedirectAttributes redirectAttributes){
        funcionarioModel.setNomeFuncionario(funcionarioModel.getNomeFuncionario().toUpperCase());
        funcionarioRepository.save(funcionarioModel);
        redirectAttributes.addFlashAttribute("mensagem","CADASTRADO COM SUCESSO!!!");
        redirectAttributes.addFlashAttribute("style","mensagem alert alert-success");
        return "redirect:/cadastroFuncionario";
    }
    @GetMapping("/listFunc")
    public String listFuncionario(@RequestParam(defaultValue = "0") int page, Model model,RedirectAttributes redirectAttributes){
        return loadFuncionarioPage(page,model,null,redirectAttributes);
    }


    @GetMapping("/listagemFuncionario")
    public String listagemFuncionario(@ModelAttribute FuncionarioModel funcionarioModel,@RequestParam(defaultValue = "0") int page, Model model,RedirectAttributes redirectAttributes){
        String filter =funcionarioModel.getNomeFuncionario().toUpperCase();

        return loadFuncionarioPage(page,model,filter,redirectAttributes);
    }

    private String loadFuncionarioPage(int page, Model model, String filter,RedirectAttributes redirectAttributes){
        Pageable pageable = PageRequest.of(page, 5);
        Page<FuncionarioModel> funcionarioPages;

        if(filter != null && !filter.isEmpty()){
            funcionarioPages = funcionarioRepository.findByNomeFuncionario(filter, pageable);
            if(funcionarioPages.getTotalElements() == 0){
                redirectAttributes.addFlashAttribute("mensagem", "NÃO EXISTE O FUNCIONARIO!!!");
                redirectAttributes.addFlashAttribute("style", "mensagem alert alert-danger");
                return "redirect:/listFunc";
            }
        }else{
            funcionarioPages = funcionarioRepository.findAll(pageable);
        }

        model.addAttribute("funcionarios", funcionarioPages.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", funcionarioPages.getTotalPages());

        if (funcionarioPages.getTotalElements() > 5) {
            model.addAttribute("totalItems", 1);
        } else {
            model.addAttribute("totalItems", 0);
        }

        List<Integer> pageNumbers = IntStream.range(0, funcionarioPages.getTotalPages())
                .boxed()
                .collect(Collectors.toList());


        model.addAttribute("pageNumbers",pageNumbers);
        model.addAttribute("funcionarioModel",new FuncionarioModel());
        model.addAttribute("filter", filter);

        return "Funcionario/Listar";
    }

    @PostMapping("/deletarFuncionario")
    public String deletarFuncionario(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes){
        funcionarioRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem","APAGADO COM SUCESSO!!!");
        redirectAttributes.addFlashAttribute("style","mensagem alert alert-success");

        return "redirect:/listFunc";
    }

    @GetMapping("/atualizarPorIdFunc")
    public String atualizarPorIdFunc(@RequestParam("id") Integer id, @RequestParam("nome") String nome, Model model){
        model.addAttribute("id",id);
        model.addAttribute("nome",nome.toUpperCase());
        return "Funcionario/Atualizar";
    }

    @PostMapping("atualizarFunc")
    @Transactional
    public String atualizarFunc(@RequestParam("id") Integer id, @RequestParam("nome") String nome, @RequestParam("cargo") String cargo,RedirectAttributes redirectAttributes){
        funcionarioRepository.updateFuncionarioModelById(id,nome.toUpperCase(),cargo);
        redirectAttributes.addFlashAttribute("mensagem","ATUALIZADO COM SUCESSO!!!");
        redirectAttributes.addFlashAttribute("style","mensagem alert alert-success");
        return "redirect:/listFunc";
    }

}
