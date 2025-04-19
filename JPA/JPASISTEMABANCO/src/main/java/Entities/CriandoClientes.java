package Entities;

import Dao.CrudSistemaReserva.CrudCliente;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CriandoClientes {
    public static void CriandoTodosOsClientes() throws SQLException {
        //SISTEMA QUE VAI CRIAR CLIENTES BASEADO NO TXT CONTENDO AS INFORMAÇÕES DOS CLIENTES;
        CrudCliente crudCliente = new CrudCliente();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/Lista_Cliente/ListaCliente.txt"))) {
            String linha;
            List<String> listPessoa = new ArrayList<>();
            List<String> listTelefone = new ArrayList<>();
            int count = 0;
            while ((linha = reader.readLine()) != null) {
                String[] lineParts = linha.split(" ");
                listPessoa.add(lineParts[0]);
                listTelefone.add(lineParts[1]);
                Cliente cliente = new Cliente(listPessoa.get(count), listTelefone.get(count));
                //APROVEITAR A ESTRUTURA DE REPETIÇÃO PARA CRIAR AS CONTAS DOS CLIENTES;
                crudCliente.inserir(cliente);
                CriadoContas.CriarContas(cliente);
                count++;
            }
            System.out.println("CLIENTES E CONTAS ADICIONADOS COM SUCESSO!!!");
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro de leitura do arquivo: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao inserir cliente no banco de dados: " + e.getMessage());
        }
    }
}
