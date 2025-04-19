package br.edu.ifpi.catce.sistemareserva.view;


import br.edu.ifpi.catce.sistemareserva.entities.ReservaEquipamento;
import br.edu.ifpi.catce.sistemareserva.entities.ReservaEspaco;
import br.edu.ifpi.catce.sistemareserva.model.ReservaEspacoModel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class ReservaEspacoView {
    public void post(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html><header><link rel='stylesheet' href='style.css'></header><body>");
        out.println("<p class='estilogenerico'> ADICIONADO COM SUCESSO <br><br><a href='http://localhost:8080/SISTEMARESERVACOMSERVLETS/'>VOLTAR A HOME</a><br></p>\n");
        out.println("</body></html>");
    }
    public void getTodos(List<ReservaEspaco> reservaEspacos , HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        out.println("<html><header><link rel='stylesheet' href='style.css'></header><body>");
        out.println("<p class='estilogenerico'>");
        for (int i = 0; i < reservaEspacos.size(); i++) {
            ReservaEspaco reservaEspaco = reservaEspacos.get(i);
            out.print("ID DA RESERVA: " + reservaEspaco.getId_reserva() + " ");
            out.print(", ID DO FUNCIONARIO: " + reservaEspaco.getFuncionario().getId());
            out.print(", ID DO ESPAÇO: " +reservaEspaco.getEspaco().getId_espaco());
            out.print(", DATA RESERVA:" + reservaEspaco.getDt_reserva() + "<br>");
        }
        out.println("<br><a href='http://localhost:8080/SISTEMARESERVACOMSERVLETS/'>VOLTAR A HOME</a><br>");
        out.println("</p><br></body></html>");
    }
    public void getNome(ReservaEspacoModel reservaEspacoModel, String nomeFuncionario, HttpServletResponse resp) throws IOException, SQLException {
        PrintWriter out = resp.getWriter();

        out.println("<html><header><link rel='stylesheet' href='style.css'></header><body>");
        out.println("<p class='estilogenerico'>"+reservaEspacoModel.buscarPorNomeFuncionario(nomeFuncionario)+"</p><br>");
        out.println("</body></html>");
    }
}
