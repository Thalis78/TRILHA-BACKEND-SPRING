package Dao.InterfaceSistemaReserva;

import Entities.ReservaEspaco;

import java.sql.SQLException;
import java.util.List;

public interface ReservaEspacoDao {
    ReservaEspaco buscarPorData() throws SQLException;

    ReservaEspaco buscarPorNomeFuncionario() throws SQLException;

    List<ReservaEspaco> buscarTodos();

    public void inserir(ReservaEspaco reservaEspaco) throws SQLException;

    public void excluir() throws SQLException;


}
