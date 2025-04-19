package br.edu.ifpi.catce.sistemareserva.model;

import br.edu.ifpi.catce.sistemareserva.crud.CrudReservaEquipamento;
import br.edu.ifpi.catce.sistemareserva.interfacesistema.ReservaEquipamentoDao;
import br.edu.ifpi.catce.sistemareserva.entities.ReservaEquipamento;

import java.sql.SQLException;
import java.util.List;

public class ReservaEquipamentoModel {
    ReservaEquipamentoDao reservaEquipamentoDao;

    public ReservaEquipamentoModel(){
        reservaEquipamentoDao = new CrudReservaEquipamento();
    }

    public String buscarPorNomeFuncionario(String nome) throws SQLException {
        return reservaEquipamentoDao.buscarPorNomeFuncionario(nome);
    }

    public List<ReservaEquipamento> buscarTodos() {
        return reservaEquipamentoDao.buscarTodos();
    }

    public void inserir(ReservaEquipamento reservaEquipamento) throws SQLException {
        reservaEquipamentoDao.inserir(reservaEquipamento);
    }

};
