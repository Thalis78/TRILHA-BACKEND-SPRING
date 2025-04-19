package Controllers;

import Entities.Funcionario;
import Entities.Reserva;
import Model.ReservaEquipamentoModel;
import Entities.ReservaEquipamento;
import View.ReservaEquipamentoView;

import java.sql.SQLException;
import java.util.List;

public class ReservaEquipamentoController {
    private ReservaEquipamentoModel reservaEquipamentoModel;
    private ReservaEquipamentoView reservaEquipamentoView;

    public ReservaEquipamentoController(ReservaEquipamentoModel reservaEquipamentoModel, ReservaEquipamentoView reservaEquipamentoView){
        this.reservaEquipamentoModel = reservaEquipamentoModel;
        this.reservaEquipamentoView  = reservaEquipamentoView;
    }
    public void BuscarPorDataReservaEquipamento(ReservaEquipamentoModel reservaEquipamentoModel) throws SQLException{
        reservaEquipamentoModel.buscarPorData();
    }
    public void BuscarPorNomeFuncionario(ReservaEquipamentoModel reservaEquipamentoModel) throws SQLException{
        reservaEquipamentoModel.buscarPorNomeFuncionario();
    }
    public void ListarReservaEquipamento(ReservaEquipamentoModel reservaEquipamentoModel){
        List<ReservaEquipamento> reservaEquipamentos = reservaEquipamentoModel.buscarTodos();
        if(reservaEquipamentos.isEmpty()){
            System.out.println("NENHUM EQUIPAMENTO REGISTRADO!!!");
        }else {
            reservaEquipamentoView.ListaReservaEquipamento(reservaEquipamentos);
        }
    }
    public void InserirReservaEquipamento(ReservaEquipamentoModel reservaEquipamentoModel, ReservaEquipamento reservaEquipamento) throws SQLException {
        reservaEquipamentoModel.inserir(reservaEquipamento);
        reservaEquipamento.reserva(reservaEquipamento);
        System.out.println("DADOS INSERIDOS COM SUCESSO!!!");
    }
    public void ExcluirReservaEquipamento(ReservaEquipamentoModel reservaEquipamentoModel) throws SQLException{
        reservaEquipamentoModel.excluir();
        System.out.println("EXCLUIDO COM SUCESSO !!!");
    }
}
