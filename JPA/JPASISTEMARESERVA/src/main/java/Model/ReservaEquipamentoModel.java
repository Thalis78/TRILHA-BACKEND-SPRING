package Model;

import Dao.CrudSistemaReserva.CrudReservaEquipamento;
import Dao.InterfaceSistemaReserva.ReservaEquipamentoDao;
import Entities.ReservaEquipamento;

import java.sql.SQLException;
import java.util.List;

public class ReservaEquipamentoModel {
    ReservaEquipamentoDao reservaEquipamentoDao;

    public ReservaEquipamentoModel(){
        reservaEquipamentoDao = new CrudReservaEquipamento();
    }

    public ReservaEquipamento buscarPorData() throws SQLException {
        return reservaEquipamentoDao.buscarPorData();
    }

    public ReservaEquipamento buscarPorNomeFuncionario() throws SQLException {
        return reservaEquipamentoDao.buscarPorNomeFuncionario();
    }

    public List<ReservaEquipamento> buscarTodos() {
        return reservaEquipamentoDao.buscarTodos();
    }

    public void inserir(ReservaEquipamento reservaEquipamento) throws SQLException {
        reservaEquipamentoDao.inserir(reservaEquipamento);
    }
    public void excluir() throws SQLException {
        reservaEquipamentoDao.excluir();
    }
    };
