package br.edu.ifpi.catce.sistemareserva.model;

import br.edu.ifpi.catce.sistemareserva.crud.CrudEquipamento;
import br.edu.ifpi.catce.sistemareserva.interfacesistema.EquipamentoDao;
import br.edu.ifpi.catce.sistemareserva.entities.Equipamento;

import java.sql.SQLException;
import java.util.List;
//MODEL É UMA CAMADA DO MVC USADO PARA FACILITAR A COMUNICAÇÃO ENTRE O SISTEMA BACKEND E BANCO DE DADOS.
public class EquipamentoModel {
    EquipamentoDao equipamentoDao ;

    public EquipamentoModel() {
        equipamentoDao = new CrudEquipamento();
    }

    public EquipamentoDao getEquipamentoDao() {
        return equipamentoDao;
    }

    public String buscarPorNome(String nome) throws SQLException {
        return equipamentoDao.buscarPorNome(nome);
    }


    public List<Equipamento> buscarTodos() {
        return equipamentoDao.buscarTodos();
    }


    public void inserir(Equipamento equipamento) throws SQLException {
        equipamentoDao.inserir(equipamento);
    }
};