package CrudSistemaReserva;

import java.sql.*;
import java.util.Scanner;

public class ExemploInjecaoSql {
    private Scanner scanner = new Scanner(System.in);

    public void buscarPorNomeVulneravel() {
        CrudDadosDaConexao dadosDaConexao = new CrudDadosDaConexao();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(dadosDaConexao.getUrl(), dadosDaConexao.getUser(), dadosDaConexao.getPassword());

            System.out.println("\n--- [MODO VULNERÁVEL: STATEMENT] ---");
            System.out.print("Digite o nome do equipamento (ou código malicioso): ");
            String inputUsuario = scanner.nextLine();

            String sql = "SELECT * FROM Equipamento WHERE nome_produto LIKE '%" + inputUsuario.toUpperCase() + "%'";

            System.out.println("Query que será enviada ao Banco: " + sql);

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            System.out.println("Resultados obtidos:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id_equipamento");
                String nome = resultSet.getString("nome_produto");
                int quantidade = resultSet.getInt("quantidade_disponivel");
                System.out.println("| ---> ID: " + id + ", NOME: " + nome + ", QUANTIDADE: " + quantidade);
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ou o comando SQL injetado quebrou a sintaxe!");
            e.printStackTrace();
        } finally {
            fecharRecursos(connection, statement, resultSet);
        }
    }

    public void buscarPorNomeSeguro() {
        CrudDadosDaConexao dadosDaConexao = new CrudDadosDaConexao();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(dadosDaConexao.getUrl(), dadosDaConexao.getUser(), dadosDaConexao.getPassword());

            System.out.println("\n--- [MODO SEGURO: PREPAREDSTATEMENT] ---");
            System.out.print("Digite o nome do equipamento (ou código malicioso): ");
            String inputUsuario = scanner.nextLine();

            String sql = "SELECT * FROM Equipamento WHERE nome_produto LIKE ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, "%" + inputUsuario.toUpperCase() + "%");

            resultSet = preparedStatement.executeQuery();

            System.out.println("Resultados obtidos:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id_equipamento");
                String nome = resultSet.getString("nome_produto");
                int quantidade = resultSet.getInt("quantidade_disponivel");
                System.out.println("| ---> ID: " + id + ", NOME: " + nome + ", QUANTIDADE: " + quantidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharRecursos(connection, preparedStatement, resultSet);
        }
    }

    private void fecharRecursos(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}