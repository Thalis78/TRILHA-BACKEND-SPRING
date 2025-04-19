package Model;

import Dao.CrudSistemaReserva.CrudEquipamento;
import Dao.InterfaceSistemaReserva.EquipamentoDao;
import Entities.Equipamento;

import java.sql.SQLException;
import java.util.List;

public class EquipamentoModel {
    EquipamentoDao equipamentoDao ;

    public EquipamentoModel() {
        equipamentoDao = new CrudEquipamento();
    }

    public EquipamentoDao getEquipamentoDao() {
        return equipamentoDao;
    }

    public Equipamento buscarPorNome() throws SQLException {
        return equipamentoDao.buscarPorNome();
    }


    public List<Equipamento> buscarTodos() {
        return equipamentoDao.buscarTodos();
    }


    public void inserir(Equipamento equipamento) throws SQLException {
        equipamentoDao.inserir(equipamento);
    }

    public void excluir() throws SQLException {
        equipamentoDao.excluir();
    }
};