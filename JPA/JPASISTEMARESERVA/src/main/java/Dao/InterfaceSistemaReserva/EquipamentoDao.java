package Dao.InterfaceSistemaReserva;

import Entities.Equipamento;

import java.sql.SQLException;
import java.util.List;

public interface EquipamentoDao {

    Equipamento buscarPorNome() throws SQLException;

    List<Equipamento> buscarTodos();

    public void inserir(Equipamento equipamento) throws SQLException;

    public void excluir() throws SQLException;

}
