package CrudSistemaReserva;

import InterfaceSistemaReserva.EquipamentoDao;
import SistemaReserva.Equipamento;

import java.net.ContentHandler;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrudEquipamento implements EquipamentoDao {
    Scanner scanner = new Scanner(System.in);

    public Equipamento buscarPorNome() throws SQLException {
        CrudDadosDaConexao dadosDaConexao = new CrudDadosDaConexao();
        Connection connection = DriverManager.getConnection(dadosDaConexao.getUrl(),dadosDaConexao.getUser(),dadosDaConexao.getPassword());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String selectsql = "SELECT * FROM Equipamento WHERE nome_produto LIKE ?";
            preparedStatement = connection.prepareStatement(selectsql);
            System.out.println("SELECIONE O NOME DO EQUIPAMENTO OU INCIAL: ");
            preparedStatement.setString(1,'%'+ scanner.next().toUpperCase()+'%');
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_equipamento");
                String nome = resultSet.getString("nome_produto");
                int quantidade = resultSet.getInt("quantidade_disponivel");
                System.out.println("| ---> ID : " + id + ", NOME : " + nome + ", QUANTIDADE DISPONIVEL: " + quantidade);
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

    public List<Equipamento> buscarTodos() {
        List<Equipamento> equipamentos = new ArrayList<>();
        CrudDadosDaConexao crudDadosDaConexao = new CrudDadosDaConexao();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(crudDadosDaConexao.getUrl(), crudDadosDaConexao.getUser(), crudDadosDaConexao.getPassword());
            String selectsql = "SELECT * FROM Equipamento";
            preparedStatement = connection.prepareStatement(selectsql);
            resultSet = preparedStatement.executeQuery();

            System.out.println(">>>>>>>>>>>>>>>>>>>>>EQUIPAMENTOS<<<<<<<<<<<<<<<<<<<<<<<<<");

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_equipamento");
                String nome = resultSet.getString("nome_produto");
                int quantidade = resultSet.getInt("quantidade_disponivel");
                Equipamento equipamento = new Equipamento(id, nome, quantidade);
                equipamentos.add(equipamento);
                System.out.println("| ---> ID : " + id + ", NOME : " + nome + ", QUANTIDADE DISPONIVEL: " + quantidade);

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
        return equipamentos;
    }

    public void inserir(Equipamento equipamento) throws SQLException {
        CrudDadosDaConexao dadosDaConexao = new CrudDadosDaConexao();
        Connection connection = DriverManager.getConnection(dadosDaConexao.getUrl(),dadosDaConexao.getUser(),dadosDaConexao.getPassword());

        PreparedStatement preparedStatement = null;

        try {
            String insertsql = "INSERT INTO EQUIPAMENTO(id_equipamento,nome_produto,quantidade_disponivel) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(insertsql);
            preparedStatement.setInt(1,equipamento.getId());
            preparedStatement.setString(2, equipamento.getNome_equipamento());
            preparedStatement.setInt(3,equipamento.getQuantidade_disponivel());
            preparedStatement.executeUpdate();
            Equipamento.informarcao_equipamento(equipamento);
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
            String Deletesql = "DELETE FROM Equipamento WHERE id_equipamento = ?";
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
