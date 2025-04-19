package br.edu.ifpi.catce.sistemareserva.controller;

import br.edu.ifpi.catce.sistemareserva.model.*;
import br.edu.ifpi.catce.sistemareserva.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
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
public class ReservaEspacoController {
    @Autowired
    EspacoRepository espacoRepository;
    @Autowired
    FuncionarioRepository funcionarioRepository;
    @Autowired
    ReservaEspacoRepository reservaEspacoRepository;

    @GetMapping("/reservaEspaco")
    public String reservaEspaco(){

        return "ReservaEspaco/Reservaespaco";
    }

    @GetMapping("/cadastroReservaEsp")
    public String cadastroReservaEqui(Model model) {
        ReservaEspacoModel reservaEspacoModel = new ReservaEspacoModel();

        List<EspacoModel> espacos= espacoRepository.findAll();
        List<FuncionarioModel> funcionarios = funcionarioRepository.findAll();
        model.addAttribute("reservaEspacoModel", reservaEspacoModel);
        model.addAttribute("espacoModelList", espacos);
        model.addAttribute("funcionarioModelList", funcionarios);

        return "ReservaEspaco/Cadastrar";
    }

    @PostMapping("/cadastroRealizadoReservaEsp")
    public String cadastroRealizadoReservaEsp(ReservaEspacoModel reservaEspacoModel, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensagem","CADASTRADO COM SUCESSO!!!");
        redirectAttributes.addFlashAttribute("style","mensagem alert alert-success");
        reservaEspacoRepository.save(reservaEspacoModel);
        return "redirect:/cadastroReservaEsp";
    }

    @GetMapping("/listReservaEsp")
    public String listReservaEsp(@RequestParam(defaultValue = "0") int page, Model model,RedirectAttributes redirectAttributes) {
        return loadReservaEspaco(page, model, null,redirectAttributes);
    }

    @GetMapping("/ListagemRealizadaReservaEsp")
    public String listagemRealizadaReservaEsp(@RequestParam(defaultValue = "0") int page,
                                              Model model,ReservaEspacoModel reservaEspacoModel,RedirectAttributes redirectAttributes) {
        String nomeEspaco = reservaEspacoModel.getEspaco().getNomeEspaco();
        return loadReservaEspaco(page, model, nomeEspaco,redirectAttributes);
    }

    private String loadReservaEspaco(int page, Model model, String filter,RedirectAttributes redirectAttributes) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<ReservaEspacoModel> reservaEspacoPages;

        if (filter != null && !filter.isEmpty()) {
            reservaEspacoPages = reservaEspacoRepository.findReservaEspacoByNomeEspaco(filter, pageable);
            if(reservaEspacoPages.getTotalElements() == 0){
                redirectAttributes.addFlashAttribute("mensagem", "NÃO EXISTE RESERVA COM ESSE ESPAÇO!!!");
                redirectAttributes.addFlashAttribute("style", "mensagem alert alert-danger");
                return "redirect:/listReservaEsp";
            }
        } else {
            reservaEspacoPages = reservaEspacoRepository.findReservaEspacoByNomeEspaco("", pageable);
        }

        model.addAttribute("reservasEspacos", reservaEspacoPages.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", reservaEspacoPages.getTotalPages());
        model.addAttribute("totalItems", reservaEspacoPages.getTotalElements());

        List<Integer> pageNumbers = IntStream.range(0, reservaEspacoPages.getTotalPages())
                .boxed()
                .collect(Collectors.toList());

        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("reservaEspacoModel", new ReservaEspacoModel());

        if (reservaEspacoPages.getTotalElements() > 5) {
            model.addAttribute("totalItems", 1);
        } else {
            model.addAttribute("totalItems", 0);
        }

        model.addAttribute("filter", filter);

        return "ReservaEspaco/Listar";
    }



    @PostMapping("/deletarReservaEsp")
    public String deletarReservaEsp(@RequestParam("id") Integer id,RedirectAttributes redirectAttributes){
        reservaEspacoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem","APAGADO COM SUCESSO!!!");
        redirectAttributes.addFlashAttribute("style","mensagem alert alert-success");
        return "redirect:/listReservaEsp";
    }

    @GetMapping("atualizarPorIdReservaEspaco")
    public String atualizarPorIdReservaEspaco(@RequestParam("id") Integer id, Model model){
        model.addAttribute("id",id);
        List<EspacoModel> espacos= espacoRepository.findAll();
        List<FuncionarioModel> funcionarios = funcionarioRepository.findAll();

        model.addAttribute("espacoModelList", espacos);
        model.addAttribute("funcionarioModelList", funcionarios);
        return "ReservaEspaco/Atualizar";

    }
    @PostMapping("atualizarReservaEspaco")
    @Transactional
    public String atualizarReservaEspaco(@RequestParam("id") Integer id,@RequestParam("espaco") Integer espaco,@RequestParam("func") Integer func,RedirectAttributes redirectAttributes){
        reservaEspacoRepository.updateReservaEspacoModelById_reserva(id,espaco,func);
        redirectAttributes.addFlashAttribute("mensagem","ATUALIZADO COM SUCESSO!!!");
        redirectAttributes.addFlashAttribute("style","mensagem alert alert-success");
        return "redirect:/listReservaEsp";
    }


}
