package br.edu.ifpi.catce.sistemareserva.view;

import br.edu.ifpi.catce.sistemareserva.entities.Espaco;
import br.edu.ifpi.catce.sistemareserva.entities.Funcionario;
import br.edu.ifpi.catce.sistemareserva.model.EspacoModel;
import br.edu.ifpi.catce.sistemareserva.model.FuncionarioModel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import static java.lang.System.out;

public class FuncionarioView {
    public void post(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        out.println("<html><header><link rel='stylesheet' href='style.css'></header><body>");
        out.println("<p class='estilogenerico'> ADICIONADO COM SUCESSO <br><br><a href='http://localhost:8080/SISTEMARESERVACOMSERVLETS/'>VOLTAR A HOME</a><br></p>\n");
        out.println("</body></html>");
    }
    public void getTodos(List<Funcionario> funcionarios,HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        out.println("<html><header><link rel='stylesheet' href='style.css'></header><body>");
        out.println("<p class='estilogenerico'>");
        for (int i = 0; i < funcionarios.size(); i++) {
            Funcionario funcionario = funcionarios.get(i);
            out.print("ID: " + funcionario.getId() + " ");
            out.print(", NOME FUNCIONARIO: " + funcionario.getNome_funcionario());
            out.print(", CARGO: " + funcionario.getCargo()+"<br>");

        }
        out.println("<br><a href='http://localhost:8080/SISTEMARESERVACOMSERVLETS/'>VOLTAR A HOME</a><br>");
        out.println("</p><br></body></html>");
    }
    public void getNome(FuncionarioModel funcionarioModel, String nomeFuncionario,HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        out.println("<html><header><link rel='stylesheet' href='style.css'></header><body>");
        try {
            out.println("<p class='estilogenerico'>"+funcionarioModel.buscarPorNome(nomeFuncionario)+"</p><br>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.println("</body></html>");
    }
}


