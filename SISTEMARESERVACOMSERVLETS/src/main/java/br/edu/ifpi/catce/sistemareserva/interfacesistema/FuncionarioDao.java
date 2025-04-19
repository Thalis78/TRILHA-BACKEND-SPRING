package br.edu.ifpi.catce.sistemareserva.interfacesistema;

import br.edu.ifpi.catce.sistemareserva.entities.Funcionario;

import java.sql.SQLException;
import java.util.List;

public interface FuncionarioDao {

    String buscarPorNome(String nome) throws SQLException;

    List<Funcionario> buscarTodos();

    public void inserir(Funcionario funcionario) throws SQLException;

}
