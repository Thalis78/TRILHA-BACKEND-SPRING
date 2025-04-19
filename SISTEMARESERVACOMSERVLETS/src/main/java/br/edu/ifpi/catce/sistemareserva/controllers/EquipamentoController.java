package br.edu.ifpi.catce.sistemareserva.controllers;

import br.edu.ifpi.catce.sistemareserva.entities.Equipamento;
import br.edu.ifpi.catce.sistemareserva.model.EquipamentoModel;
import br.edu.ifpi.catce.sistemareserva.view.EquipamentoView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
//CONTROLLERS É UMA CAMADA DO MVC QUE É USADO PARA FAZER A COMUNICAÇÃO ENTRE A VIEW E O MODEL;
//CONTROLLER É RESPONSAVEL POR RECEBER AS REQUISIÇÕES DO USUARIOS E MANDAR A RESPOSTA NECESSARIA PRO USUARIO;
//SERVLETS SÃO CLASSES JAVA QUE SÃO UTILIZADAS PARA GERAR RESPOSTAS DINÂMICAS A PARTIR DE REQUISIÇÕES HTTP
public class EquipamentoController extends HttpServlet {
    EquipamentoModel equipamentoModel = new EquipamentoModel();
    EquipamentoView equipamentoView = new EquipamentoView();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomeEquipamento = req.getParameter("nomeEquipamento").toUpperCase();
        int quantidadeStr =Integer.parseInt(req.getParameter("quant"));
        Equipamento equipamento = new Equipamento(nomeEquipamento,quantidadeStr);
        try {
            equipamentoModel.inserir(equipamento);
            equipamentoView.post(resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomeEquipamento = req.getParameter("nome");
        boolean letrasNomes = nomeEquipamento == "";
        List<Equipamento> equipamentos = equipamentoModel.buscarTodos();
        if(letrasNomes){
            equipamentoView.getTodos(equipamentos,resp);
        }else{
            equipamentoView.getNome(equipamentoModel,nomeEquipamento,resp);

        }
    }
}
