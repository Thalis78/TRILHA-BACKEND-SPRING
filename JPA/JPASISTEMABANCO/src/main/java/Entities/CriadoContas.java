package Entities;

import Dao.CrudSistemaReserva.CrudConta;

import java.sql.SQLException;

public class CriadoContas {
    public static void CriarContas(Cliente cliente) throws SQLException {
            Conta conta = new Conta("1111-01","88888888-81","CONTA POUPANÇA",cliente);
            CrudConta crudConta = new CrudConta();
            crudConta.inserir(conta);
    }
}
