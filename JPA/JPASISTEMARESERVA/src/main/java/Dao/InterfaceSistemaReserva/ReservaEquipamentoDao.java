package Dao.InterfaceSistemaReserva;

import Entities.ReservaEquipamento;
import java.sql.SQLException;
import java.util.List;

public interface ReservaEquipamentoDao {
    ReservaEquipamento buscarPorData() throws SQLException;

    ReservaEquipamento buscarPorNomeFuncionario() throws SQLException;

    List<ReservaEquipamento> buscarTodos();

    public void inserir(ReservaEquipamento reservaEquipamento) throws SQLException;

    public void excluir() throws SQLException;


}
