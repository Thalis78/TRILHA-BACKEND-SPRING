package br.edu.ifpi.catce.sistemareserva.controllers;

import br.edu.ifpi.catce.sistemareserva.entities.Espaco;
import br.edu.ifpi.catce.sistemareserva.model.EspacoModel;
import br.edu.ifpi.catce.sistemareserva.view.EspacoView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EspacoController extends HttpServlet {
    EspacoModel espacoModel = new EspacoModel();
    EspacoView espacoView = new EspacoView();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomeEspaco = req.getParameter("nomeEspaco").toUpperCase();
        String status = req.getParameter("stat").toUpperCase();
        Espaco espaco = new Espaco(nomeEspaco,status);
        try {
            espacoModel.inserir(espaco);
            espacoView.post(resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomeEspaco = req.getParameter("nome");
        boolean letrasNomes = nomeEspaco == "";
        List<Espaco> espacos = espacoModel.buscarTodos();
        if(letrasNomes){
            espacoView.getTodos(espacos,resp);
        }else{
            espacoView.getNome(espacoModel,nomeEspaco,resp);
        }
    }
}