package br.edu.ifpi.catce.sistemareserva.controller;

import br.edu.ifpi.catce.sistemareserva.model.EquipamentoModel;
import br.edu.ifpi.catce.sistemareserva.repository.EquipamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
// ISSO INDICA QUE ESSA CLASSE É UMA CONTROLLER QUE VAI SER MAPEADA PELO SPRING NA EXECUÇÃO.
@Controller
public class EquipamentoController {
    //ESSA ANOTAÇÃO É USADA PARA INDICAR QUE UMA DEPENDÊNCIA DEVE SER INJETADA
    //SPRING BUSCA UMA ISTÂNCIA DA CLASSE CORRESPONDENTE  E A INJETA ALI.
    @Autowired
    EquipamentoRepository equipamentoRepository;
    //REQUESIÇÃO VIA GET;
    @GetMapping("/equipamento")
    public String equipamento(){
        return "Equipamento/Equipamento";
    }

    @GetMapping("/cadastroEquipamento")
    //MODEL É USADO PARA TRANSFERIR DADOS ENTRE A CONTROLLER E A VIEW NA MESMA REQUISIÇÃO.
    public String cadastroEquipamento(Model model){
        //ADDATRIBUTE VAI ADICIONAR UMA NOVA INSTÂNCIA DE UM OBJETO DE UMA CLASSE
        //VAI PERMITIR QUE A VIEW ACESSE OS DADOS DO OBJETO DURANTE A RENDERIZAÇÃO.
        //ESSE ATRIBUTOS SÃO TEMPORÁRIOS E EXISTEM APENAS DURANTE O PROCESSAMENTO DA REQUESIÇÃO ATUAL. APÓS A VIEW SER RENDERIZADA, OS ATRIBUTOS NÃO ESTÃO MAIS DISPONÍVEIS.
        model.addAttribute(new EquipamentoModel());
        return "Equipamento/Cadastrar";
    }
    //REQUESIÇÃO VIA POST
    @PostMapping("/cadastroRealizado")
    //REDIRECTATTRIBUTES USADO PARA PASSAR ATRIBUTOS DE UMA REQUESIÇÃO PARA OUTRA DURANTE UM REDIRECIONAMENTO.
    //ELE VAO USADO PARA PASSAR DADOS ENTRE REQUISIÇÕES APÓS UM REDIRECIONAMENTO.
    //FLASH ATTRIBUTES É USADO PARA ADICIONAR ATRIBUTOS TEMPORÁRIOS E DESAPARECEM APÓS A PRÓXIMA REQUISIÇÃO.
    //ESTÃO DISPONÍVEIS APENAS NA PRÓXIMA REQUISIÇÃO.
    public String cadastroRealizado(EquipamentoModel equipamentoModel, RedirectAttributes redirectAttributes){
        equipamentoModel.setNomeEquipamento(equipamentoModel.getNomeEquipamento().toUpperCase());
        equipamentoRepository.save(equipamentoModel);
        redirectAttributes.addFlashAttribute("mensagem", "CADASTRADO COM SUCESSO!!!");
        redirectAttributes.addFlashAttribute("style", "mensagem alert alert-success");

        return "redirect:/cadastroEquipamento";

    }
    // O CONTROLADOR RETORNA A PRIMEIRA PÁGINA COM TODOS OS EQUIPAMENTOS COM LOTE DE 5 POR VEZ.
    @GetMapping("/listEquipamento")
    // REQUESTPARAM É USADO PARA ESPECIFICAR QUE IREMOS RECEBER UM DADO EM UMA REQUISIÇÃO
    // DEFINIMOS UM VALOR PADRÃO PRA CASO O PARÂMETRO NÃO SEJA FORNECIDO NA REQUISIÇÃO.
    public String listEquipamento(@RequestParam(defaultValue = "0") int page,RedirectAttributes redirectAttributes, Model model) {
        return loadEquipamentoPage(page, model, null,redirectAttributes);
    }

    //CONTEM DADOS DO FORMULÁRIO, ESPECIFICAMENTE O NOME DO EQUIPAMENTO PARA FILTRAGEM.
    @GetMapping("/listagemEquipamento")
    //MODELATTRIBUTE VAI SER USADO PARA VINCULAR DADOS DE UM FORMULÁRIO A UM OBJETO EM UM MÉTODO DO CONTROLADOR.
    public String listagemEquipamento(@ModelAttribute EquipamentoModel equipamentoModel,
                                      Model model,RedirectAttributes redirectAttributes,
                                      @RequestParam(defaultValue = "0") int page) {
        String filter = equipamentoModel.getNomeEquipamento().toUpperCase();



        // PASSA A PÁGINA ATUAL COM O FILTRO;

        return loadEquipamentoPage(page, model, filter,redirectAttributes);
    }

    // VAI MOSTRAR TODOS OS RESULTADOS PAGINADO EM LOTES DE 5 POR VEZ.
    // LÓGICA USADA PARA FORMATAR OS DADOS DA VISUALIZAÇÃO, INDEPENDETEMENTE DE O USUÁRIO ESTAR FILTRANDO OU NÃO.
    private String loadEquipamentoPage(int page, Model model, String filter,RedirectAttributes redirectAttributes) {

        //PAGEABLE É UMA INTERFACE QUE ENCAPSULA INFORMAÇÕES SOBRE A SOLICITAÇÃO DE UMA PÁGINA DE DADOS.
        //ISSO INCLUI O NÚMERO DE PÁGINA, O TAMANHO DA PÁGINA E,OPCIONALMENTE, A ODERNAÇÃO DOS RESULTADOS.
        Pageable pageable = PageRequest.of(page, 5);
        //É USADO PARA REPRESENTAR UMA PÁGINA DE RESULTADOS EM CONSULTAS PÁGINADAS.
        // NELA VAI CONTER INFORMAÇÕES DOS DADOS E A QUANTIDADE DE NÚMEROS DE PÁGINA.
        Page<EquipamentoModel> equipamentosPage;
        //CASO O FILTRO SEJA DIFERENTE DE NULOS OU DIFERENTE DE VAZIA;
        //RETORNA A FILTRAGEM
        if (filter != null && !filter.isEmpty()) {
            equipamentosPage = equipamentoRepository.findByNomeEquipamento(filter, pageable);
            if(equipamentosPage.getTotalElements() == 0){
                redirectAttributes.addFlashAttribute("mensagem", "NÃO EXISTE O EQUIPAMENTO!!!");
                redirectAttributes.addFlashAttribute("style", "mensagem alert alert-danger");
                return "redirect:/listEquipamento";
            }
        } else {
            //RETORNA TODOS OS ELEMENTOS QUE EXISTEM NO BANCO DE DADOS
            equipamentosPage = equipamentoRepository.findAll(pageable);
        }

        model.addAttribute("equipamentos", equipamentosPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", equipamentosPage.getTotalPages());


        if (equipamentosPage.getTotalElements() > 5) {
            model.addAttribute("totalItems", 1);
        } else {
            model.addAttribute("totalItems", 0);
        }

        List<Integer> pageNumbers = IntStream.range(0, equipamentosPage.getTotalPages())
                .boxed()
                .collect(Collectors.toList());
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("equipamentoModel", new EquipamentoModel());
        model.addAttribute("filter", filter);

        return "Equipamento/Listar";
    }





    @PostMapping("/deletarEquipamento")
    public String deletarEquipamento(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes){
        equipamentoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem","APAGADO COM SUCESSO!!!");
        redirectAttributes.addFlashAttribute("style","mensagem alert alert-success");
        return "redirect:/listEquipamento";
    }


    @GetMapping("/atualizarPorIdEqui")
    public String idRecebido(@RequestParam("id")Integer id,@RequestParam("nome")  String nome, @RequestParam("quant") int quant ,Model model){
        model.addAttribute("id",id);
        model.addAttribute("nome",nome.toUpperCase());
        model.addAttribute("quant",quant);
        return "Equipamento/Atualizar";
    }

    @PostMapping("/atualizarEquipamento")
    @Transactional
    public String atualizarEquipamento(@RequestParam("id") Integer id,@RequestParam("nome")  String nome, @RequestParam("quant") int quant, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("mensagem","ATUALIZADO COM SUCESSO!!!");
        redirectAttributes.addFlashAttribute("style","mensagem alert alert-success");
        equipamentoRepository.updateEquipamentoModelById(id,nome.toUpperCase(),quant);
        return "redirect:/listEquipamento";
    }


}
