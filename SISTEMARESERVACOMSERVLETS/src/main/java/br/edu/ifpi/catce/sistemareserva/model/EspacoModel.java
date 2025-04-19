package br.edu.ifpi.catce.sistemareserva.model;

import br.edu.ifpi.catce.sistemareserva.crud.CrudEspaco;
import br.edu.ifpi.catce.sistemareserva.interfacesistema.EspacoDao;
import br.edu.ifpi.catce.sistemareserva.entities.Espaco;

import java.sql.SQLException;
import java.util.List;

public class EspacoModel {
    EspacoDao espacoDao;

    public EspacoModel() {
        espacoDao = new CrudEspaco();
    }

    public String buscarPorNome(String nome) throws SQLException {
        return espacoDao.buscarPorNome(nome);
    }


    public List<Espaco> buscarTodos() {
        return espacoDao.buscarTodos();
    }


    public void inserir(Espaco espaco) throws SQLException {
        espacoDao.inserir(espaco);
    }

    };
