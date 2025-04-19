package br.edu.ifpi.catce.sistemareserva.view;

import br.edu.ifpi.catce.sistemareserva.entities.Equipamento;
import br.edu.ifpi.catce.sistemareserva.entities.Funcionario;
import br.edu.ifpi.catce.sistemareserva.entities.ReservaEquipamento;
import br.edu.ifpi.catce.sistemareserva.entities.ReservaEspaco;
import br.edu.ifpi.catce.sistemareserva.model.EquipamentoModel;
import br.edu.ifpi.catce.sistemareserva.model.FuncionarioModel;
import br.edu.ifpi.catce.sistemareserva.model.ReservaEquipamentoModel;
import br.edu.ifpi.catce.sistemareserva.model.ReservaEspacoModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class ReservaEquipamentoView {
    public void post(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html><header><link rel='stylesheet' href='style.css'></header><body>");
        out.println("<p class='estilogenerico'> ADICIONADO COM SUCESSO <br><br><a href='http://localhost:8080/SISTEMARESERVACOMSERVLETS/'>VOLTAR A HOME</a><br></p>\n");
        out.println("</body></html>");
    }
    public void getTodos(List<ReservaEquipamento> reservaEquipamentos , HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        out.println("<html><header><link rel='stylesheet' href='style.css'></header><body>");
        out.println("<p class='estilogenerico'>");
        for (int i = 0; i < reservaEquipamentos.size(); i++) {
            ReservaEquipamento reservaEquipamento = reservaEquipamentos.get(i);
            out.print("ID DA RESERVA: " + reservaEquipamento.getId_reserva() + " ");
            out.print(", ID DO FUNCIONARIO: " + reservaEquipamento.getFuncionario().getId());
            out.print(", ID DO EQUIPAMENTO: " +reservaEquipamento.getEquipamento().getId_equipamento());
            out.print(", DATA RESERVA:" + reservaEquipamento.getDt_reserva() + "<br>");
        }
        out.println("<br><a href='http://localhost:8080/SISTEMARESERVACOMSERVLETS/'>VOLTAR A HOME</a><br>");
        out.println("</p><br></body></html>");
    }
    public void getNome(ReservaEquipamentoModel reservaEquipamentoModel, String nomeFuncionario, HttpServletResponse resp) throws IOException, SQLException {
        PrintWriter out = resp.getWriter();

        out.println("<html><header><link rel='stylesheet' href='style.css'></header><body>");
        out.println("<p class='estilogenerico'>"+reservaEquipamentoModel.buscarPorNomeFuncionario(nomeFuncionario)+"</p><br>");
        out.println("</body></html>");
    }


}
