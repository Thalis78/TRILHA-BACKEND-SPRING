package Principal;

import Dao.CrudSistemaReserva.CrudCliente;
import Dao.CrudSistemaReserva.CrudConta;
import Entities.Cliente;
import Entities.CriandoClientes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) throws SQLException {
        CriandoClientes.CriandoTodosOsClientes();
        CrudCliente crudCliente = new CrudCliente();
        crudCliente.buscarTodos();
        crudCliente.buscarPorNome();
        CrudConta crudConta = new CrudConta();
        crudConta.buscarPorID();
        crudConta.buscarTodos();
        crudConta.excluir();
    }

}
