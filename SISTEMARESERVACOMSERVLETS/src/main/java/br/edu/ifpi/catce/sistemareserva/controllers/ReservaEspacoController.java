package br.edu.ifpi.catce.sistemareserva.controllers;

import br.edu.ifpi.catce.sistemareserva.entities.Espaco;
import br.edu.ifpi.catce.sistemareserva.entities.Funcionario;
import br.edu.ifpi.catce.sistemareserva.entities.ReservaEspaco;
import br.edu.ifpi.catce.sistemareserva.model.EspacoModel;
import br.edu.ifpi.catce.sistemareserva.model.FuncionarioModel;
import br.edu.ifpi.catce.sistemareserva.model.ReservaEspacoModel;
import br.edu.ifpi.catce.sistemareserva.view.ReservaEspacoView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReservaEspacoController extends HttpServlet {
    ReservaEspacoModel reservaEspacoModel = new ReservaEspacoModel();
    ReservaEspacoView reservaEspacoView = new ReservaEspacoView();
    FuncionarioModel funcionarioModel = new FuncionarioModel();
    EspacoModel espacoModel = new EspacoModel();


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idFuncionario = Integer.parseInt(req.getParameter("funcionario"));
        int idEspaco = Integer.parseInt(req.getParameter("espaco"));
        Funcionario funcionario = funcionarioModel.buscarTodos().get(idFuncionario-1);
        Espaco espaco = espacoModel.buscarTodos().get(idEspaco-1);
        String data = req.getParameter("data");
        ReservaEspaco reservaEspaco = new ReservaEspaco(data, espaco, funcionario);
        try {
            reservaEspacoModel.inserir(reservaEspaco);
            reservaEspacoView.post(resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomeFuncionario = req.getParameter("nomeFunc");
        boolean letrasNomes = nomeFuncionario == "";
        List<ReservaEspaco> reservaEspacos = reservaEspacoModel.buscarTodos();
        if(letrasNomes){
            reservaEspacoView.getTodos(reservaEspacos,resp);
        }
        else{
            try {
                reservaEspacoView.getNome(reservaEspacoModel,nomeFuncionario,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}