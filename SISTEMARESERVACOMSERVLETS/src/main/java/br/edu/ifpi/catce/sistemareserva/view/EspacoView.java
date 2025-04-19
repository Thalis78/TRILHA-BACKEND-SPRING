package br.edu.ifpi.catce.sistemareserva.view;

import br.edu.ifpi.catce.sistemareserva.entities.Espaco;
import br.edu.ifpi.catce.sistemareserva.model.EspacoModel;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import static java.lang.System.out;

public class EspacoView {
    public void post(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        out.println("<html><header><link rel='stylesheet' href='style.css'></header><body>");
        out.println("<p class='estilogenerico'> ADICIONADO COM SUCESSO <br><br><a href='http://localhost:8080/SISTEMARESERVACOMSERVLETS/'>VOLTAR A HOME</a><br></p>\n");
        out.println("</body></html>");
    }
    public void getTodos(List<Espaco> espacos,HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        out.println("<html><header><link rel='stylesheet' href='style.css'></header><body>");
        out.println("<p class='estilogenerico'>");
        for (int i = 0; i < espacos.size(); i++) {
            Espaco espaco = espacos.get(i);
            out.print("ID: " + espaco.getId_espaco() + " ");
            out.print(", NOME ESPAÇO: " + espaco.getNome_espaco());
            out.print(", STATUS: " + espaco.getStatus()+"<br>");

        }
        out.println("<br><a href='http://localhost:8080/SISTEMARESERVACOMSERVLETS/'>VOLTAR A HOME</a><br>");
        out.println("</p><br></body></html>");
    }
    public void getNome(EspacoModel espacoModel, String nomeEspaco, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        out.println("<html><header><link rel='stylesheet' href='style.css'></header><body>");
        try {
            out.println("<p class='estilogenerico'>"+espacoModel.buscarPorNome(nomeEspaco)+"</p><br>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.println("</body></html>");
    }
}
