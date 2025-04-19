package br.edu.ifpi.catce.sistemareserva.model;

import br.edu.ifpi.catce.sistemareserva.crud.CrudFuncionario;
import br.edu.ifpi.catce.sistemareserva.interfacesistema.FuncionarioDao;
import br.edu.ifpi.catce.sistemareserva.entities.Funcionario;

import java.sql.SQLException;
import java.util.List;

public class FuncionarioModel {
    FuncionarioDao funcionarioDao;
    public FuncionarioModel(){
        funcionarioDao = new CrudFuncionario();
    }
    public String buscarPorNome(String nome) throws SQLException {
        return funcionarioDao.buscarPorNome(nome);
    }

    public List<Funcionario> buscarTodos() {
        return funcionarioDao.buscarTodos();
    }

    public void inserir(Funcionario funcionario) throws SQLException {
        funcionarioDao.inserir(funcionario);
    }
    };

