package br.edu.ifpi.catce.sistemareserva.controller;

import br.edu.ifpi.catce.sistemareserva.model.EspacoModel;
import br.edu.ifpi.catce.sistemareserva.repository.EspacoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class EspacoController {
    @Autowired
    EspacoRepository espacoRepository;

    @GetMapping("/espaco")
    public String espaco(){
        return "Espaco/Espaco";
    }

    @GetMapping("/cadastroEspaco")
    public String cadastroEspaco(Model model){
        model.addAttribute(new EspacoModel());
        return "Espaco/Cadastrar";
    }

    @PostMapping("/cadastroRealizadoEsp")
    public String cadastroRealizadoEsp(EspacoModel espacoModel, RedirectAttributes redirectAttributes){
        espacoModel.setNomeEspaco(espacoModel.getNomeEspaco().toUpperCase());
        espacoRepository.save(espacoModel);
        redirectAttributes.addFlashAttribute("mensagem","CADASTRADO COM SUCESSO!!!");
        redirectAttributes.addFlashAttribute("style","mensagem alert alert-success");
        return "redirect:/cadastroEspaco";
    }

    @GetMapping("/listEspaco")
    public String listEspaco(@RequestParam(defaultValue = "0") int page, Model model,RedirectAttributes redirectAttributes) {
        return loadEspacoPage(page, model, null,redirectAttributes);
    }

    @GetMapping("/listagemEspaco")
    public String listagemEspaco(@ModelAttribute EspacoModel espacoModel, Model model, @RequestParam(defaultValue = "0") int page,RedirectAttributes redirectAttributes) {
        String filter = espacoModel.getNomeEspaco().toUpperCase();
        return loadEspacoPage(page, model, filter,redirectAttributes);
    }

    private String loadEspacoPage(int page, Model model, String filter,RedirectAttributes redirectAttributes) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<EspacoModel> espacosPage;

        if (filter != null && !filter.isEmpty()) {
            espacosPage = espacoRepository.findByNomeEspaco(filter, pageable);
            if(espacosPage.getTotalElements() == 0){
                redirectAttributes.addFlashAttribute("mensagem", "NÃO EXISTE O ESPAÇO!!!");
                redirectAttributes.addFlashAttribute("style", "mensagem alert alert-danger");
                return "redirect:/listEspaco";
            }
        } else {
            espacosPage = espacoRepository.findAll(pageable);
        }

        model.addAttribute("espacos", espacosPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", espacosPage.getTotalPages());

        if (espacosPage.getTotalElements() > 5) {
            model.addAttribute("totalItems", 1);
        } else {
            model.addAttribute("totalItems", 0);
        }

        List<Integer> pageNumbers = IntStream.range(0, espacosPage.getTotalPages())
                .boxed()
                .collect(Collectors.toList());

        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("espacoModel", new EspacoModel());
        model.addAttribute("filter", filter);

        return "Espaco/Listar";
    }
    @PostMapping("/deletarEspaco")
    public String deletarEspaco(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes){
        espacoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem","APAGADO COM SUCESSO!!!");
        redirectAttributes.addFlashAttribute("style","mensagem alert alert-success");

        return "redirect:/listEspaco";
    }

    @GetMapping("/atualizarPorIdEsp")
    public String atualizarPorIdEsp(@RequestParam("id") Integer id,@RequestParam("nome") String nome, Model model){
        model.addAttribute("id",id);
        model.addAttribute("nome",nome);
        return "Espaco/Atualizar";
    }

    @PostMapping("/atualizarEspaco")
    @Transactional
    public String atualizarEspaco(@RequestParam("id") Integer id,@RequestParam("nome") String nome,@RequestParam("status") String status, RedirectAttributes redirectAttributes){
        espacoRepository.updateEspacoById(id,nome.toUpperCase(),status);
        redirectAttributes.addFlashAttribute("mensagem","ATUALIZADO COM SUCESSO!!!");
        redirectAttributes.addFlashAttribute("style","mensagem alert alert-success");
        return "redirect:/listEspaco";
    }


}
