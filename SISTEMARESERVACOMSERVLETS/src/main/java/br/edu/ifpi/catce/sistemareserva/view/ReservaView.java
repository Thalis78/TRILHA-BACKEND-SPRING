package br.edu.ifpi.catce.sistemareserva.view;

import br.edu.ifpi.catce.sistemareserva.entities.Equipamento;
import br.edu.ifpi.catce.sistemareserva.entities.Espaco;
import br.edu.ifpi.catce.sistemareserva.entities.Funcionario;
import br.edu.ifpi.catce.sistemareserva.model.EquipamentoModel;
import br.edu.ifpi.catce.sistemareserva.model.EspacoModel;
import br.edu.ifpi.catce.sistemareserva.model.FuncionarioModel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ReservaView {
    FuncionarioModel funcionarioModel = new FuncionarioModel();
    EquipamentoModel equipamentoModel = new EquipamentoModel();
    EspacoModel espacoModel = new EspacoModel();
    public void reservaEquipamento(HttpServletResponse resp) throws IOException {

        List<Funcionario> funcionarios = funcionarioModel.buscarTodos();
        List<Equipamento> equipamentos = equipamentoModel.buscarTodos();
        PrintWriter out = resp.getWriter();

        out.println("<html><head><link rel='stylesheet' href='style.css'></head><body>");
        out.println("<form method='post' style='height: 350px' class='cadastrar' action='http://localhost:8080/SISTEMARESERVACOMSERVLETS/RESERVAEQUIPAMENTO'>");
        out.println("<div class='countainer'>");
        out.println("<br>");
        out.println("<h3>RESERVAR EQUIPAMENTO:</h3>");
        out.println("<br>");
        out.println("</div>");
        out.println("<div class='countainer countainer1'>");

        out.println("<label for='funcionario'>SELECIONE UM FUNCIONÁRIO:</label><br>");
        out.println("<select style=\"width: 200px;text-align: center;height: 30px\" id='funcionario' name='funcionario'required>");
        for (Funcionario funcionario : funcionarios) {
            out.println("<option value='" + funcionario.getId() + "'>" + funcionario.getNome_funcionario()+ "</option>");
        }
        out.println("</select><br><br>");

        out.println("<label for='equipamento'>SELECIONE UM EQUIPAMENTO:</label><br>");
        out.println("<select style=\"width: 200px;text-align: center;height: 30px\"id='equipamento' name='equipamento'required>");
        for (Equipamento equipamento : equipamentos) {
            out.println("<option value='" + equipamento.getId_equipamento() + "'>" + equipamento.getNome_equipamento() + "</option>");
        }
        out.println("</select><br><br>");

        out.println("<label for='data'>DATA DA RESERVA(AAAA-MM-DD):</label><br>");
        out.println("<input style=\"width: 200px;text-align: center;height: 30px\" type='Date' id='data' name='data'required><br>");
        out.println("</div>");
        out.println("<div class='countainer'>");
        out.println("<input type='submit' value='CADASTRAR'>");
        out.println("</div>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");

    }

    public void reservaEspaco(HttpServletResponse resp) throws IOException {
        List<Funcionario> funcionarios = funcionarioModel.buscarTodos();
        List<Espaco> espacos = espacoModel.buscarTodos();

        PrintWriter out = resp.getWriter();

        out.println("<html><head><link rel='stylesheet' href='style.css'></head><body>");
        out.println("<form method='post' style='height: 350px' class='cadastrar' action='http://localhost:8080/SISTEMARESERVACOMSERVLETS/RESERVAESPACO'>");
        out.println("<div class='countainer'>");
        out.println("<br>");
        out.println("<h3>RESERVAR ESPAÇO:</h3>");
        out.println("<br>");
        out.println("</div>");
        out.println("<div class='countainer countainer1'>");

        out.println("<label for='funcionario'>SELECIONE UM FUNCIONÁRIO:</label><br>");
        out.println("<select style=\"width: 200px;text-align: center;height: 30px\" id='funcionario' name='funcionario'required>");
        for (Funcionario funcionario : funcionarios) {
            out.println("<option value='" + funcionario.getId() + "'>" + funcionario.getNome_funcionario() + "</option>");
        }
        out.println("</select><br><br>");

        out.println("<label for='espaco'>SELECIONE UM ESPAÇO:</label><br>");
        out.println("<select style=\"width: 200px;text-align: center;height: 30px\" id='espaco' name='espaco'>required");
        for (Espaco espaco : espacos) {
            out.println("<option value='" + espaco.getId_espaco() + "'>" + espaco.getNome_espaco() + "</option>");
        }
        out.println("</select><br><br>");

        out.println("<label for='data'>DATA DA RESERVA(AAAA-MM-DD):</label><br>");
        out.println("<input style=\"width: 200px;text-align: center;height: 30px\" type='Date' id='data' name='data'required><br>");
        out.println("</div>");
        out.println("<div class='countainer'>");
        out.println("<input type='submit' value='CADASTRAR'>");
        out.println("</div>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");

    }


}
