package Dao.InterfaceSistemaReserva;

import Entities.Conta;

import java.sql.SQLException;
import java.util.List;

public interface ContaDao {
    Conta buscarPorID() throws SQLException;

    List<Conta> buscarTodos() throws SQLException;

    void inserir(Conta conta) throws SQLException;

    void excluir() throws SQLException;
}
