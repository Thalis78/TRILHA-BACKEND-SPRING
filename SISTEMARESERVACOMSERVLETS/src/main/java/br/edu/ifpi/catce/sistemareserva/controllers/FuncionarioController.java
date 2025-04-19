package br.edu.ifpi.catce.sistemareserva.controllers;

import br.edu.ifpi.catce.sistemareserva.entities.Funcionario;
import br.edu.ifpi.catce.sistemareserva.model.FuncionarioModel;
import br.edu.ifpi.catce.sistemareserva.view.FuncionarioView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioController extends HttpServlet {
    FuncionarioModel funcionarioModel = new FuncionarioModel();
    FuncionarioView funcionarioView = new FuncionarioView();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nomeFuncionario = req.getParameter("nomeFuncionario").toUpperCase();
        String cargo = req.getParameter("cargo").toUpperCase();
        Funcionario funcionario = new Funcionario(nomeFuncionario,cargo);
        try {
            funcionarioModel.inserir(funcionario);
            funcionarioView.post(resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomeFuncionario = req.getParameter("nomeFunc");
        boolean letrasNomes = nomeFuncionario == "";
        List<Funcionario> funcionarios = funcionarioModel.buscarTodos();
        if(letrasNomes){
            funcionarioView.getTodos(funcionarios,resp);
        }
        else{
            funcionarioView.getNome(funcionarioModel,nomeFuncionario,resp);
        }
    }
}