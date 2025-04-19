package CrudSistemaReserva;

import java.sql.*;


public class CrudConexaoComBd{
    public static void main(String[] args) throws SQLException {
        CrudDadosDaConexao dadosConexao = new CrudDadosDaConexao();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dadosConexao.getUrl(), dadosConexao.getUser(), dadosConexao.getPassword());
            System.out.println("CONECTADO COM O BANCO DE DADOS!!!");
        } catch (SQLException e) {
            System.out.println("ALGUNS ERROS ACONTECERAM DURANTE A EXECUÇÃO!!");

            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    System.out.println("CONEXÃO FECHADA!!");
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
