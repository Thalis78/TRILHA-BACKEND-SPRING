package br.edu.ifpi.catce.sistemareserva.view;

import br.edu.ifpi.catce.sistemareserva.entities.Equipamento;
import br.edu.ifpi.catce.sistemareserva.model.EquipamentoModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import static java.lang.System.out;

//VIEW É UMA CAMADA DO MVC UTILIZADA PARA INTERAÇÃO COM USUARIO E PELA APRESENTAÇÃO DOS DADOS.
public class EquipamentoView {

    public void post(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        out.println("<html><header><link rel='stylesheet' href='style.css'></header><body>");
        out.println("<p class='estilogenerico'> ADICIONADO COM SUCESSO <br><br><a href='http://localhost:8080/SISTEMARESERVACOMSERVLETS/'>VOLTAR A HOME</a><br></p>\n");
        out.println("</body></html>");
    }
    public void getTodos(List<Equipamento> equipamentos,HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        out.println("<html><header><link rel='stylesheet' href='style.css'></header><body>");
        out.println("<p class='estilogenerico'>");
        for (int i = 0; i < equipamentos.size(); i++) {
            Equipamento equipamento = equipamentos.get(i);
            out.print("ID: " + equipamento.getId_equipamento() + " ");
            out.print(", NOME EQUIPAMENTO: " + equipamento.getNome_equipamento());
            out.print(", QUANTIDADE DISPONÍVEL: " + equipamento.getQuantidade_disponivel()+"<br>");

        }
        out.println("<br><a href='http://localhost:8080/SISTEMARESERVACOMSERVLETS/'>VOLTAR A HOME</a><br>");
        out.println("</p><br></body></html>");
    }
    public void getNome(EquipamentoModel equipamentoModel, String nomeEquipamento, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        out.println("<html><header><link rel='stylesheet' href='style.css'></header><body>");
        try {
            out.println("<p class='estilogenerico'>"+equipamentoModel.buscarPorNome(nomeEquipamento)+"</p><br>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.println("</body></html>");
    }

}
