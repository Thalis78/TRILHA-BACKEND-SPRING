package Principal;

import Controllers.*;
import Model.*;
import Utils.*;
import View.*;

import java.sql.SQLException;

public class Main{
    public static void main(String[] args) throws SQLException {
        //      INSTÂNCIA DAS CLASSES
        Equipamento equipamento = new Equipamento(1,"EQUIPAMENTO 03",100);
        Espaco espaco = new Espaco(1,"ESPAÇO 01","A");
        Funcionario funcionario = new Funcionario(1,"JOÃO","NORMAL");
        ReservaEquipamento reservaEquipamento = new ReservaEquipamento(1,"2024-10-10", funcionario.getId(), espaco.getId());
        ReservaEspaco reservaEspaco = new ReservaEspaco(1,"2024-05-05", funcionario.getId(), espaco.getId());

        EquipamentoModel equipamentoModel = new EquipamentoModel();
        EquipamentoView equipamentoView = new EquipamentoView();
        EquipamentoController equipamentoController = new EquipamentoController(equipamentoModel,equipamentoView);

        EspacoModel espacoModel = new EspacoModel();
        EspacoView espacoView = new EspacoView();
        EspacoController espacoController = new EspacoController(espacoModel,espacoView);

        FuncionarioModel funcionarioModel = new FuncionarioModel();
        FuncionarioView funcionarioView = new FuncionarioView();
        FuncionarioController funcionarioController = new FuncionarioController(funcionarioModel,funcionarioView);

        ReservaEquipamentoModel reservaEquipamentoModel = new ReservaEquipamentoModel();
        ReservaEquipamentoView reservaEquipamentoView = new ReservaEquipamentoView();
        ReservaEquipamentoController reservaEquipamentoController = new ReservaEquipamentoController(reservaEquipamentoModel,reservaEquipamentoView);

        ReservaEspacoModel reservaEspacoModel = new ReservaEspacoModel();
        ReservaEspacoView reservaEspacoView = new ReservaEspacoView();
        ReservaEspacoController reservaEspacoController = new ReservaEspacoController(reservaEspacoModel,reservaEspacoView);
        //      INSERIR DADOS:
        equipamentoController.InserirEquipamentos(equipamentoModel , equipamento);
        espacoController.InserirEspaco(espacoModel, espaco);
        funcionarioController.InserirFuncionario(funcionarioModel, funcionario);
        reservaEquipamentoController.InserirReservaEquipamento(reservaEquipamentoModel, reservaEquipamento);
        reservaEspacoController.InserirReservaEspaco(reservaEspacoModel, reservaEspaco);
        //      BUSCAR POR NOME:
        equipamentoController.BuscarPorNomeEquipamento(equipamentoModel);
        espacoController.BuscarPorNomeEspaco(espacoModel);
        funcionarioController.BuscarPorNomeFuncionario(funcionarioModel);
        reservaEquipamentoController.BuscarPorNomeFuncionario(reservaEquipamentoModel);
        reservaEspacoController.BuscarPorNomeFuncionario(reservaEspacoModel);
        //      BUSCAR POR DATA:
        reservaEquipamentoController.BuscarPorDataReservaEquipamento(reservaEquipamentoModel);
        reservaEspacoController.BuscarPorDataReservaEspaco(reservaEspacoModel);
        //      LISTAR TODOS OS DADOS:
        equipamentoController.ListarEquipamentos(equipamentoModel);
        espacoController.ListarEspaco(espacoModel);
        funcionarioController.ListarFuncionario(funcionarioModel);
        reservaEquipamentoController.ListarReservaEquipamento(reservaEquipamentoModel);
        reservaEspacoController.ListarReservaEspaco(reservaEspacoModel);
        //      EXCLUIR DADOS:
        reservaEquipamentoController.ExcluirReservaEquipamento(reservaEquipamentoModel);
        reservaEspacoController.ExcluirReservaEspaco(reservaEspacoModel);
        equipamentoController.ExcluirEquipamentos(equipamentoModel);
        espacoController.ExcluirEspaco(espacoModel);
        funcionarioController.ExcluirFuncionario(funcionarioModel);



    }
}
