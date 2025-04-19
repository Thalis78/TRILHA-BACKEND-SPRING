package br.edu.ifpi.catce.sistemareserva.interfacesistema;

import br.edu.ifpi.catce.sistemareserva.entities.ReservaEspaco;

import java.sql.SQLException;
import java.util.List;

public interface ReservaEspacoDao {
    String buscarPorNomeFuncionario(String nome) throws SQLException;

    List<ReservaEspaco> buscarTodos();

    public void inserir(ReservaEspaco reservaEspaco) throws SQLException;

}
