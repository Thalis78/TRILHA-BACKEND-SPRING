package Controllers;

import Model.ReservaEspacoModel;
import Entities.ReservaEspaco;
import View.ReservaEspacoView;

import java.sql.SQLException;
import java.util.List;

public class ReservaEspacoController {
    private ReservaEspacoModel reservaEspacoModel;
    private ReservaEspacoView reservaEspacoView;

    public ReservaEspacoController(ReservaEspacoModel reservaEquipamentoModel, ReservaEspacoView reservaEspacoView){
        this.reservaEspacoModel = reservaEquipamentoModel;
        this.reservaEspacoView = reservaEspacoView;
    }
    public void BuscarPorDataReservaEspaco(ReservaEspacoModel reservaEspacoModel) throws SQLException{
        reservaEspacoModel.buscarPorData();
    }
    public void BuscarPorNomeFuncionario(ReservaEspacoModel reservaEspacoModel) throws  SQLException{
        reservaEspacoModel.buscarPorNomeFuncionario();
    }
    public void ListarReservaEspaco(ReservaEspacoModel reservaEspacoModel){
        List<ReservaEspaco> reservaEspacos = reservaEspacoModel.buscarTodos();
        if(reservaEspacos.isEmpty()){
            System.out.println("NENHUM EQUIPAMENTO REGISTRADO!!!");
        }else {
            reservaEspacoView.ListaReservaEspaco(reservaEspacos);
        }
    }
    public void InserirReservaEspaco(ReservaEspacoModel reservaEspacoModel, ReservaEspaco reservaEspaco) throws SQLException {
        reservaEspacoModel.inserir(reservaEspaco);
        reservaEspaco.reserva(reservaEspaco);
        System.out.println("DADOS INSERIDOS COM SUCESSO!!!");
    }
    public void ExcluirReservaEspaco(ReservaEspacoModel reservaEspacoModel) throws SQLException {
        reservaEspacoModel.excluir();
        System.out.println("EXCLUIDO COM SUCESSO!!!");
    }

}
