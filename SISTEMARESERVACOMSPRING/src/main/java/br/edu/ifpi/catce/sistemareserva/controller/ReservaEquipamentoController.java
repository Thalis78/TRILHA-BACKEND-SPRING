package br.edu.ifpi.catce.sistemareserva.controller;

import br.edu.ifpi.catce.sistemareserva.model.EquipamentoModel;
import br.edu.ifpi.catce.sistemareserva.model.FuncionarioModel;
import br.edu.ifpi.catce.sistemareserva.model.ReservaEquipamentoModel;
import br.edu.ifpi.catce.sistemareserva.model.ReservaEspacoModel;
import br.edu.ifpi.catce.sistemareserva.repository.EquipamentoRepository;
import br.edu.ifpi.catce.sistemareserva.repository.FuncionarioRepository;
import br.edu.ifpi.catce.sistemareserva.repository.ReservaEquipamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ReservaEquipamentoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ReservaEquipamentoRepository reservaEquipamentoRepository;

    @GetMapping("/reservaEquipamento")
    public String reservaEquipamento() {
        return "ReservaEquipamento/Reservaequipamento";
    }

    @GetMapping("/cadastroReservaEqui")
    public String cadastroReservaEqui(Model model) {
        List<EquipamentoModel> equipamentos = equipamentoRepository.findAll();
        List<FuncionarioModel> funcionarios = funcionarioRepository.findAll();

        model.addAttribute("reservaEquipamentoModel", new ReservaEquipamentoModel());
        model.addAttribute("equipamentoModelList", equipamentos);
        model.addAttribute("funcionarioModelList", funcionarios);

        return "ReservaEquipamento/Cadastrar";
    }

    @PostMapping("/cadastroRealizadoReservaEqui")
    public String cadastroRealizadoReservaEqui(@ModelAttribute ReservaEquipamentoModel reservaEquipamentoModel,
                                               RedirectAttributes redirectAttributes) {
        reservaEquipamentoRepository.save(reservaEquipamentoModel);
        redirectAttributes.addFlashAttribute("mensagem", "CADASTRADO COM SUCESSO!!!");
        redirectAttributes.addFlashAttribute("style", "mensagem alert alert-success");
        return "redirect:/cadastroReservaEqui";
    }
    @GetMapping("/listReservaEqui")
    public String listReservaEqui(@RequestParam(defaultValue = "0") int page, Model model,RedirectAttributes redirectAttributes) {
        return loadReservaEquipamento(page, model, null,redirectAttributes);
    }

    @GetMapping("/ListagemRealizadaReservaEqui")
    public String listagemRealizadaReservaEqui(@RequestParam(defaultValue = "0") int page,
                                              Model model,ReservaEquipamentoModel reservaEquipamentoModel,RedirectAttributes redirectAttributes) {
        String nomeEquipamento = reservaEquipamentoModel.getEquipamento().getNomeEquipamento();
        return loadReservaEquipamento(page, model, nomeEquipamento,redirectAttributes);
    }

    private String loadReservaEquipamento(int page, Model model, String filter,RedirectAttributes redirectAttributes) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<ReservaEquipamentoModel> reservaEquipamentoPages;

        if (filter != null && !filter.isEmpty()) {
            reservaEquipamentoPages = reservaEquipamentoRepository.findReservaEquipamentoByNomeEquipamento(filter, pageable);
            if(reservaEquipamentoPages.getTotalElements() == 0){
                redirectAttributes.addFlashAttribute("mensagem", "NÃO EXISTE RESERVA COM ESSE EQUIPAMENTO!!!");
                redirectAttributes.addFlashAttribute("style", "mensagem alert alert-danger");
                return "redirect:/listReservaEqui";
            }
        } else {
            reservaEquipamentoPages = reservaEquipamentoRepository.findReservaEquipamentoByNomeEquipamento("", pageable);
        }

        model.addAttribute("reservasEquipamentos", reservaEquipamentoPages.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", reservaEquipamentoPages.getTotalPages());
        model.addAttribute("totalItems", reservaEquipamentoPages.getTotalElements());

        List<Integer> pageNumbers = IntStream.range(0, reservaEquipamentoPages.getTotalPages())
                .boxed()
                .collect(Collectors.toList());

        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("reservaEquipamentoModel", new ReservaEquipamentoModel());

        if (reservaEquipamentoPages.getTotalElements() > 5) {
            model.addAttribute("totalItems", 1);
        } else {
            model.addAttribute("totalItems", 0);
        }

        model.addAttribute("filter", filter);

        return "ReservaEquipamento/Listar";
    }


    @PostMapping("/deletarReservaEqui")
    public String deletarReservaEqui(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        reservaEquipamentoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "APAGADO COM SUCESSO!!!");
        redirectAttributes.addFlashAttribute("style", "mensagem alert alert-success");
        return "redirect:/listReservaEqui";
    }

    @GetMapping("/atualizarPorIdReservaEquipamento")
    public String atualizarPorIdReservaEquipamento(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("equipamentosModelList", equipamentoRepository.findAll());
        model.addAttribute("funcionarioModelList", funcionarioRepository.findAll());
        return "ReservaEquipamento/Atualizar";
    }

    @PostMapping("/atualizarReservaEquipamento")
    @Transactional
    public String atualizarReservaEquipamento(@RequestParam("id") Integer id,
                                              @RequestParam("equipamento") Integer equipamento,
                                              @RequestParam("func") Integer func,
                                              RedirectAttributes redirectAttributes) {
        reservaEquipamentoRepository.updateReservaModelById_reserva(id, equipamento, func);
        redirectAttributes.addFlashAttribute("mensagem", "ATUALIZADO COM SUCESSO!!!");
        redirectAttributes.addFlashAttribute("style", "mensagem alert alert-success");
        return "redirect:/listReservaEqui";
    }
}
