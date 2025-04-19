package br.edu.ifpi.catce.sistemareserva.model;

import br.edu.ifpi.catce.sistemareserva.crud.CrudReservaEspaco;
import br.edu.ifpi.catce.sistemareserva.interfacesistema.ReservaEspacoDao;
import br.edu.ifpi.catce.sistemareserva.entities.ReservaEspaco;

import java.sql.SQLException;
import java.util.List;

public class ReservaEspacoModel {
    ReservaEspacoDao reservaEspacoDao;

    public ReservaEspacoModel() {
        this.reservaEspacoDao = new CrudReservaEspaco();
    }

    public String buscarPorNomeFuncionario(String nome) throws SQLException{
        return reservaEspacoDao.buscarPorNomeFuncionario(nome);
    };

    public List<ReservaEspaco> buscarTodos(){
        return reservaEspacoDao.buscarTodos();
    };

    public void inserir(ReservaEspaco reservaEspaco) throws SQLException{
        reservaEspacoDao.inserir(reservaEspaco);
    };
};
