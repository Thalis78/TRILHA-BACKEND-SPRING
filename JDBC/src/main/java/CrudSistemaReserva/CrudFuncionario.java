package CrudSistemaReserva;

import InterfaceSistemaReserva.FuncionarioDao;
import SistemaReserva.Equipamento;
import SistemaReserva.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrudFuncionario implements FuncionarioDao {
    Scanner scanner = new Scanner(System.in);

    public Funcionario buscarPorNome() throws SQLException {
        CrudDadosDaConexao dadosDaConexao = new CrudDadosDaConexao();
        Connection connection = DriverManager.getConnection(dadosDaConexao.getUrl(),dadosDaConexao.getUser(),dadosDaConexao.getPassword());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String selectsql = "SELECT * FROM Funcionario WHERE nome_funcionario LIKE ?";
            preparedStatement = connection.prepareStatement(selectsql);
            System.out.println("SELECIONE O NOME DO FUNCIONARIO OU INICIAL: ");
            preparedStatement.setString(1,'%'+ scanner.next()+'%');
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_funcionario");
                String nome = resultSet.getString("nome_funcionario");
                String cargo = resultSet.getString("cargo_funcionario");
                System.out.println("| ---> ID : " + id + ", NOME : " + nome + ", CARGO: " + cargo);

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


    public List<Funcionario> buscarTodos() {
        List<Funcionario> Funcionarios = new ArrayList<>();
        CrudDadosDaConexao crudDadosDaConexao = new CrudDadosDaConexao();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(crudDadosDaConexao.getUrl(), crudDadosDaConexao.getUser(), crudDadosDaConexao.getPassword());
            String selectsql = "SELECT * FROM Funcionario";
            preparedStatement = connection.prepareStatement(selectsql);
            resultSet = preparedStatement.executeQuery();

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>FUNCIONARIOS<<<<<<<<<<<<<<<<<<<<<<<<<");

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_funcionario");
                String nome = resultSet.getString("nome_funcionario");
                String cargo = resultSet.getString("cargo_funcionario");
                Funcionario funcionario = new Funcionario(id,nome,cargo);
                Funcionarios.add(funcionario);
                System.out.println("| ---> ID : " + id + ", NOME : " + nome + ", CARGO: " + cargo);

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
        return Funcionarios;
    }
    public void inserir(Funcionario funcionario) throws SQLException {
        CrudDadosDaConexao dadosDaConexao = new CrudDadosDaConexao();
        Connection connection = DriverManager.getConnection(dadosDaConexao.getUrl(),dadosDaConexao.getUser(),dadosDaConexao.getPassword());

        PreparedStatement preparedStatement = null;

        try {
            String insertsql = "INSERT INTO Funcionario(id_funcionario,nome_funcionario,cargo_funcionario) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(insertsql);
            preparedStatement.setInt(1,funcionario.getId());
            preparedStatement.setString(2, funcionario.getNome());
            preparedStatement.setString(3,funcionario.getCargo());
            preparedStatement.executeUpdate();
            Funcionario.informacao_funcionario(funcionario);
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
            String Deletesql = "DELETE FROM Funcionario WHERE id_funcionario = ?";
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
