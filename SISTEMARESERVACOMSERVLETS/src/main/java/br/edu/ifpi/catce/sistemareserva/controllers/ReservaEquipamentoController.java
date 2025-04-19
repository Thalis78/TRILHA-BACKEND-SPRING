package br.edu.ifpi.catce.sistemareserva.controllers;

import br.edu.ifpi.catce.sistemareserva.entities.Equipamento;
import br.edu.ifpi.catce.sistemareserva.entities.Funcionario;
import br.edu.ifpi.catce.sistemareserva.entities.ReservaEquipamento;
import br.edu.ifpi.catce.sistemareserva.entities.ReservaEspaco;
import br.edu.ifpi.catce.sistemareserva.model.EquipamentoModel;
import br.edu.ifpi.catce.sistemareserva.model.FuncionarioModel;
import br.edu.ifpi.catce.sistemareserva.model.ReservaEquipamentoModel;
import br.edu.ifpi.catce.sistemareserva.view.ReservaEquipamentoView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ReservaEquipamentoController extends HttpServlet {
    ReservaEquipamentoModel reservaEquipamentoModel = new ReservaEquipamentoModel();
    ReservaEquipamentoView reservaEquipamentoView = new ReservaEquipamentoView();
    FuncionarioModel funcionarioModel = new FuncionarioModel();
    EquipamentoModel equipamentoModel = new EquipamentoModel();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idFuncionario = Integer.parseInt(req.getParameter("funcionario"));
        int idEquipamento = Integer.parseInt(req.getParameter("equipamento"));
        Funcionario funcionario = funcionarioModel.buscarTodos().get(idFuncionario-1);
        Equipamento equipamento = equipamentoModel.buscarTodos().get(idEquipamento-1);
        String data = req.getParameter("data");
        ReservaEquipamento reservaEquipamento = new ReservaEquipamento(data, equipamento, funcionario);
        try {
            reservaEquipamentoModel.inserir(reservaEquipamento);
            reservaEquipamentoView.post(resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomeFuncionario = req.getParameter("nomeFunc");
        boolean letrasNomes = nomeFuncionario == "";
        List<ReservaEquipamento> reservaEquipamentos = reservaEquipamentoModel.buscarTodos();
        if(letrasNomes){
            reservaEquipamentoView.getTodos(reservaEquipamentos,resp);
        }
        else{
            try {
                reservaEquipamentoView.getNome(reservaEquipamentoModel,nomeFuncionario,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}