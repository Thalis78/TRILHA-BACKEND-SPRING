package Model;

import Dao.CrudSistemaReserva.CrudFuncionario;
import Dao.InterfaceSistemaReserva.FuncionarioDao;
import Entities.Funcionario;

import java.sql.SQLException;
import java.util.List;

public class FuncionarioModel {
    FuncionarioDao funcionarioDao;
    public FuncionarioModel(){
        funcionarioDao = new CrudFuncionario();
    }
    public Funcionario buscarPorNome() throws SQLException {
        return funcionarioDao.buscarPorNome();
    }

    public List<Funcionario> buscarTodos() {
        return funcionarioDao.buscarTodos();
    }

    public void inserir(Funcionario funcionario) throws SQLException {
        funcionarioDao.inserir(funcionario);
    }
    public void excluir() throws SQLException {
        funcionarioDao.excluir();
        }
    };

