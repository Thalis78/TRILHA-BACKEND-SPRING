package Model;

import Dao.CrudSistemaReserva.CrudEspaco;
import Dao.InterfaceSistemaReserva.EspacoDao;
import Entities.Espaco;

import java.sql.SQLException;
import java.util.List;

public class EspacoModel {
    EspacoDao espacoDao;

    public EspacoModel() {
        espacoDao = new CrudEspaco();
    }

    public Espaco buscarPorNome() throws SQLException {
        return espacoDao.buscarPorNome();
    }


    public List<Espaco> buscarTodos() {
        return espacoDao.buscarTodos();
    }


    public void inserir(Espaco espaco) throws SQLException {
        espacoDao.inserir(espaco);
    }


    public void excluir() throws SQLException {
        espacoDao.excluir();

    }
    };
