package br.edu.ifpi.catce.sistemareserva.controllers;

import br.edu.ifpi.catce.sistemareserva.entities.Equipamento;
import br.edu.ifpi.catce.sistemareserva.entities.Espaco;
import br.edu.ifpi.catce.sistemareserva.entities.Funcionario;
import br.edu.ifpi.catce.sistemareserva.view.ReservaView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReservasControllers extends HttpServlet {
    ReservaView reservaView = new ReservaView();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipoReserva = req.getParameter("tipoReserva");
        if(tipoReserva.equals("EQ")){
            reservaView.reservaEquipamento(resp);
        }else{
            reservaView.reservaEspaco(resp);
        }
    }
}
