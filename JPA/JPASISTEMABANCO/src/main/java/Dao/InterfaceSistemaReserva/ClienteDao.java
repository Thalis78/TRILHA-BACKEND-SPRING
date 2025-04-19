package Dao.InterfaceSistemaReserva;

import Entities.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDao {
    Cliente buscarPorNome() throws SQLException;

    List<Cliente> buscarTodos() throws SQLException;

    void inserir(Cliente cliente) throws SQLException;

    void excluir() throws SQLException;

}
