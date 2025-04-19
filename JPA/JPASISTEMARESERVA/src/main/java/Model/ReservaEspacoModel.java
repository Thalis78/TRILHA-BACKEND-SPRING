package Model;

import Dao.CrudSistemaReserva.CrudReservaEspaco;
import Dao.InterfaceSistemaReserva.ReservaEspacoDao;
import Entities.ReservaEspaco;

import java.sql.SQLException;
import java.util.List;

public class ReservaEspacoModel {
    ReservaEspacoDao reservaEspacoDao;

    public ReservaEspacoModel() {
        this.reservaEspacoDao = new CrudReservaEspaco();
    }
    public ReservaEspaco buscarPorData() throws SQLException{
        return reservaEspacoDao.buscarPorData();
    };

    public ReservaEspaco buscarPorNomeFuncionario() throws SQLException{
        return reservaEspacoDao.buscarPorNomeFuncionario();
    };

    public List<ReservaEspaco> buscarTodos(){
        return reservaEspacoDao.buscarTodos();
    };

    public void inserir(ReservaEspaco reservaEspaco) throws SQLException{
        reservaEspacoDao.inserir(reservaEspaco);
    };

    public void excluir() throws SQLException{
        reservaEspacoDao.excluir();
    }
};
