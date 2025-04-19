package br.edu.ifpi.catce.sistemareserva.interfacesistema;

import br.edu.ifpi.catce.sistemareserva.entities.ReservaEquipamento;
import java.sql.SQLException;
import java.util.List;

public interface ReservaEquipamentoDao {
    String buscarPorNomeFuncionario(String nome) throws SQLException;

    List<ReservaEquipamento> buscarTodos();

    public void inserir(ReservaEquipamento reservaEquipamento) throws SQLException;

}
