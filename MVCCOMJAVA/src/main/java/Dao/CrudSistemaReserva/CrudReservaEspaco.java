package Dao.CrudSistemaReserva;

import Dao.InterfaceSistemaReserva.ReservaEspacoDao;
import Utils.ParamentrosDaConexao;
import Utils.ReservaEspaco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrudReservaEspaco implements ReservaEspacoDao {
    Scanner scanner = new Scanner(System.in);

    public ReservaEspaco buscarPorData() throws SQLException {
        ParamentrosDaConexao paramentrosDaConexao = new ParamentrosDaConexao();
        Connection connection = DriverManager.getConnection(paramentrosDaConexao.getUrl(),paramentrosDaConexao.getUser(),paramentrosDaConexao.getPassword());

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String selectsql = "SELECT * FROM Reserva_espaco WHERE dt_reserva LIKE ?";
            preparedStatement = connection.prepareStatement(selectsql);
            System.out.println("SELECIONE ATRAVÉS DE UMA DATA OU ANO: ");
            preparedStatement.setString(1,'%'+ scanner.next()+'%');
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_reserva");
                String data = resultSet.getString("dt_reserva");
                Integer id_funcionario = resultSet.getInt("id_funcionario");
                Integer id_espaco = resultSet.getInt("id_espaco");
                System.out.println("| ---> ID : " + id + ", DATA : " + data + ", ID_FUNCIONARIO: " + id_funcionario + ", ID_ESPACO: " + id_espaco);

            }
        }
        catch (Exception e) {
            System.out.println("A DATA QUE VOCÊ ESCOLHEU NÃO EXISTE!!!");
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


        public ReservaEspaco buscarPorNomeFuncionario() throws SQLException {
            ParamentrosDaConexao paramentrosDaConexao = new ParamentrosDaConexao();
            Connection connection = DriverManager.getConnection(paramentrosDaConexao.getUrl(),paramentrosDaConexao.getUser(),paramentrosDaConexao.getPassword());

            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                String selectsql = "SELECT * FROM Reserva_espaco WHERE id_funcionario =(SELECT id_funcionario FROM Funcionario WHERE nome_funcionario LIKE ?)";
                preparedStatement = connection.prepareStatement(selectsql);
                System.out.println("SELECIONE O NOME DO FUNCIONARIO OU INICIAL: ");
                preparedStatement.setString(1,'%'+ scanner.next().toUpperCase()+'%');
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id_reserva");
                    String data = resultSet.getString("dt_reserva");
                    Integer id_funcionario = resultSet.getInt("id_funcionario");
                    Integer id_espaco = resultSet.getInt("id_espaco");
                    System.out.println("| ---> ID : " + id + ", DATA : " + data + ", ID_FUNCIONARIO: " + id_funcionario + ", ID_ESPACO: " + id_espaco);

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


    public List<ReservaEspaco> buscarTodos() {
        List<ReservaEspaco> reservaEspacos = new ArrayList<>();
        ParamentrosDaConexao paramentrosDaConexao = new ParamentrosDaConexao();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(paramentrosDaConexao.getUrl(),paramentrosDaConexao.getUser(),paramentrosDaConexao.getPassword());
            String selectsql = "SELECT * FROM Reserva_espaco";
            preparedStatement = connection.prepareStatement(selectsql);
            resultSet = preparedStatement.executeQuery();

            System.out.println("------------------------RESERVA DE ESPACO-----------------------");

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_reserva");
                String data = resultSet.getString("dt_reserva");
                Integer id_funcionario = resultSet.getInt("id_funcionario");
                Integer id_espaco = resultSet.getInt("id_espaco");
                ReservaEspaco reservaEspaco = new ReservaEspaco(id,data,id_funcionario,id_espaco);
                reservaEspacos.add(reservaEspaco);
                System.out.println("| ---> ID : " + id + ", DATA : " + data + ", ID_FUNCIONARIO: " + id_funcionario + ", ID_ESPACO: " + id_espaco);

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
        return reservaEspacos;
    }

    public void inserir(ReservaEspaco reservaEspaco) throws SQLException {
        ParamentrosDaConexao paramentrosDaConexao = new ParamentrosDaConexao();
        Connection connection = DriverManager.getConnection(paramentrosDaConexao.getUrl(),paramentrosDaConexao.getUser(),paramentrosDaConexao.getPassword());

        PreparedStatement preparedStatement = null;

        try {
            String insertsql = "INSERT INTO Reserva_espaco(id_reserva,dt_reserva,id_funcionario,id_espaco) VALUES (?,?,?,?)";
            preparedStatement = connection.prepareStatement(insertsql);
            preparedStatement.setInt(1,reservaEspaco.getId());
            preparedStatement.setString(2, reservaEspaco.getDt_reserva());
            preparedStatement.setInt(3,reservaEspaco.getId_funcionario());
            preparedStatement.setInt(4, reservaEspaco.getId_espaco());
            preparedStatement.executeUpdate();
            reservaEspaco.reserva(reservaEspaco,reservaEspaco.getId_espaco());
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
            String Deletesql = "DELETE FROM Reserva_Espaco WHERE id_reserva = ?";
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
