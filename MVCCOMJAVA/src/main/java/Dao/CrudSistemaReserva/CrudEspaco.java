package Dao.CrudSistemaReserva;

import Dao.InterfaceSistemaReserva.EspacoDao;
import Utils.Espaco;
import Utils.ParamentrosDaConexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrudEspaco implements EspacoDao {
    Scanner scanner = new Scanner(System.in);

    public Espaco buscarPorNome() throws SQLException {
        ParamentrosDaConexao paramentrosDaConexao = new ParamentrosDaConexao();
        Connection connection = DriverManager.getConnection(paramentrosDaConexao.getUrl(),paramentrosDaConexao.getUser(),paramentrosDaConexao.getPassword());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String selectsql = "SELECT * FROM Espaco WHERE nome_espaco LIKE ?";
            preparedStatement = connection.prepareStatement(selectsql);
            System.out.println("SELECIONE O NOME DO ESPACO OU INCIAL: ");
            preparedStatement.setString(1,'%'+ scanner.next().toUpperCase()+'%');
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_espaco");
                String nome = resultSet.getString("nome_espaco");
                String status = resultSet.getString("status");
                System.out.println("| ---> ID : " + id + ", NOME : " + nome + ", STATUS: " + status);

            }
        }
        catch (Exception e) {
            System.out.println("O NOME QUE VOCÊ ESCOLHEU NÃO EXISTE!!!");
            e.printStackTrace();
        }
        finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Espaco buscarPorNome(String nome) {
        return null;
    }

    public List<Espaco> buscarTodos() {
        List<Espaco> Espacos = new ArrayList<>();
        ParamentrosDaConexao paramentrosDaConexao = new ParamentrosDaConexao();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(paramentrosDaConexao.getUrl(),paramentrosDaConexao.getUser(),paramentrosDaConexao.getPassword());
            String selectsql = "SELECT * FROM Espaco";
            preparedStatement = connection.prepareStatement(selectsql);
            resultSet = preparedStatement.executeQuery();

            System.out.println("--------------------------ESPACO--------------------------------");

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_espaco");
                String nome = resultSet.getString("nome_espaco");
                String status = resultSet.getString("status");
                Espaco espaco = new Espaco(id, nome, status);
                Espacos.add(espaco);
                System.out.println("| ---> ID : " + id + ", NOME : " + nome + ", STATUS: " + status);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Espacos;
    }

    public void inserir(Espaco espaco) throws SQLException {
        ParamentrosDaConexao paramentrosDaConexao = new ParamentrosDaConexao();
        Connection connection = DriverManager.getConnection(paramentrosDaConexao.getUrl(),paramentrosDaConexao.getUser(),paramentrosDaConexao.getPassword());
        PreparedStatement preparedStatement = null;

        try {
            String insertsql = "INSERT INTO Espaco(id_espaco,nome_espaco,status) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(insertsql);
            preparedStatement.setInt(1,espaco.getId());
            preparedStatement.setString(2, espaco.getNome_espaco());
            preparedStatement.setString(3,espaco.getStatus());
            preparedStatement.executeUpdate();
            espaco.informacao_espaco(espaco);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void excluir() throws SQLException {
        buscarTodos();
        ParamentrosDaConexao paramentrosDaConexao = new ParamentrosDaConexao();
        Connection connection = DriverManager.getConnection(paramentrosDaConexao.getUrl(),paramentrosDaConexao.getUser(),paramentrosDaConexao.getPassword());
        PreparedStatement preparedStatement = null;
        try {
            String Deletesql = "DELETE FROM Espaco WHERE id_espaco = ?";
            preparedStatement = connection.prepareStatement(Deletesql);
            System.out.println("ID: ");
            preparedStatement.setInt(1, scanner.nextInt());
            preparedStatement.executeUpdate();
            System.out.println("APAGADO COM SUCESSO!!!");
        } catch (Exception e) {
            System.out.println("NÃO FOI POSSIVEL APAGAR!!!");
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
