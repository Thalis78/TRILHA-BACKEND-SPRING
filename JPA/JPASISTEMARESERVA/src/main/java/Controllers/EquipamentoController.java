package Controllers;

import Model.EquipamentoModel;
import Entities.Equipamento;
import View.EquipamentoView;

import java.sql.SQLException;
import java.util.List;

public class EquipamentoController {
    private EquipamentoModel equipamentoModel;
    private EquipamentoView equipamentoView;

    public EquipamentoController(EquipamentoModel equipamentoModel,EquipamentoView equipamentoView) {
        this.equipamentoModel = equipamentoModel;
        this.equipamentoView = equipamentoView;
    }
    public void BuscarPorNomeEquipamento(EquipamentoModel equipamentoModel) throws SQLException{
        equipamentoModel.buscarPorNome();
    }
    public void ListarEquipamentos(EquipamentoModel equipamentoModel){
        List<Equipamento> equipamentos = equipamentoModel.buscarTodos();
        if(equipamentos.isEmpty()){
            System.out.println("NENHUM EQUIPAMENTO REGISTRADO!!!");
        }else {
            equipamentoView.ListaEquipamento(equipamentos);
        }
    }
    public void InserirEquipamentos(EquipamentoModel equipamentoModel,Equipamento equipamento) throws SQLException {
        equipamentoModel.inserir(equipamento);
        equipamento.informarcao_equipamento(equipamento);
    }
    public void ExcluirEquipamentos(EquipamentoModel equipamentoModel) throws SQLException{
        equipamentoModel.excluir();
        System.out.println("EXCLUIDO COM SUCESSO!!!");
    }

}
