package CrudSistemaReserva;

import InterfaceSistemaReserva.ReservaEquipamentoDao;
import SistemaReserva.ReservaEquipamento;
import SistemaReserva.ReservaEspaco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrudReservaEquipamento implements ReservaEquipamentoDao {
    Scanner scanner = new Scanner(System.in);

    public ReservaEquipamento buscarPorData() throws SQLException {
        CrudDadosDaConexao dadosDaConexao = new CrudDadosDaConexao();
        Connection connection = DriverManager.getConnection(dadosDaConexao.getUrl(),dadosDaConexao.getUser(),dadosDaConexao.getPassword());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String selectsql = "SELECT * FROM Reserva_equipamento WHERE dt_reserva LIKE ?";
            preparedStatement = connection.prepareStatement(selectsql);
            System.out.println("SELECIONE ATRAVÉS DE UMA DATA OU ANO: ");
            preparedStatement.setString(1,'%'+ scanner.next()+'%');
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_reserva");
                String data = resultSet.getString("dt_reserva");
                Integer id_funcionario = resultSet.getInt("id_funcionario");
                Integer id_espaco = resultSet.getInt("id_equipamento");
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

    public ReservaEquipamento buscarPorNomeFuncionario() throws SQLException {
        CrudDadosDaConexao dadosDaConexao = new CrudDadosDaConexao();
        Connection connection = DriverManager.getConnection(dadosDaConexao.getUrl(),dadosDaConexao.getUser(),dadosDaConexao.getPassword());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String selectsql = "SELECT * FROM Reserva_Equipamento WHERE id_funcionario =(SELECT id_funcionario FROM Funcionario WHERE nome_funcionario LIKE ?)";
            preparedStatement = connection.prepareStatement(selectsql);
            System.out.println("SELECIONE O NOME DO FUNCIONARIO OU INICIAL: ");
            preparedStatement.setString(1,'%'+ scanner.next().toUpperCase()+'%');
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_reserva");
                String data = resultSet.getString("dt_reserva");
                Integer id_funcionario = resultSet.getInt("id_funcionario");
                Integer id_equipamento = resultSet.getInt("id_equipamento");
                System.out.println("| ---> ID : " + id + ", DATA : " + data + ", ID_FUNCIONARIO: " + id_funcionario + ", ID_EQUIPAMENTO: " + id_equipamento);
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


    public List<ReservaEquipamento> buscarTodos() {
        List<ReservaEquipamento> reservaEquipamentos = new ArrayList<>();
        CrudDadosDaConexao crudDadosDaConexao = new CrudDadosDaConexao();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(crudDadosDaConexao.getUrl(), crudDadosDaConexao.getUser(), crudDadosDaConexao.getPassword());
            String selectsql = "SELECT * FROM Reserva_equipamento";
            preparedStatement = connection.prepareStatement(selectsql);
            resultSet = preparedStatement.executeQuery();

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>RESERVAS DE EQUIPAMENTO <<<<<<<<<<<<<<<<<<<<<<<<<");

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_reserva");
                String data = resultSet.getString("dt_reserva");
                Integer id_funcionario = resultSet.getInt("id_funcionario");
                Integer id_equipamento = resultSet.getInt("id_equipamento");
                ReservaEquipamento reservaEquipamento = new ReservaEquipamento(id,data,id_funcionario,id_equipamento);
                reservaEquipamentos.add(reservaEquipamento);
                System.out.println("| ---> ID : " + id + ", DATA : " + data + ", ID_FUNCIONARIO: " + id_funcionario + ", ID_EQUIPAMENTO: " + id_equipamento);

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
        return reservaEquipamentos;
    }

    public void inserir(ReservaEquipamento reservaEquipamento) throws SQLException {
        CrudDadosDaConexao dadosDaConexao = new CrudDadosDaConexao();
        Connection connection = DriverManager.getConnection(dadosDaConexao.getUrl(),dadosDaConexao.getUser(),dadosDaConexao.getPassword());

        PreparedStatement preparedStatement = null;

        try {
            String insertsql = "INSERT INTO Reserva_equipamento(id_reserva,dt_reserva,id_funcionario,id_equipamento) VALUES (?,?,?,?)";
            preparedStatement = connection.prepareStatement(insertsql);
            preparedStatement.setInt(1,reservaEquipamento.getId());
            preparedStatement.setString(2, reservaEquipamento.getDt_reserva());
            preparedStatement.setInt(3,reservaEquipamento.getId_funcionario());
            preparedStatement.setInt(4, reservaEquipamento.getId_equipamento());
            preparedStatement.executeUpdate();
            reservaEquipamento.reserva(reservaEquipamento,reservaEquipamento.getId_equipamento());
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
        CrudDadosDaConexao dadosDaConexao = new CrudDadosDaConexao();
        Connection connection = DriverManager.getConnection(dadosDaConexao.getUrl(),dadosDaConexao.getUser(),dadosDaConexao.getPassword());

        PreparedStatement preparedStatement = null;
        try {
            String Deletesql = "DELETE FROM Reserva_Equipamento WHERE id_reserva = ?";
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
