package Principal;

import Controllers.*;
import Entities.*;
import Model.*;
import View.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        //INSTANCIANDO AS CLASSES:
        Equipamento equipamento = new Equipamento("EQUIPAMENTO01",50);
        Espaco espaco = new Espaco("ESPACO01","A");
        Funcionario funcionario1 = new Funcionario("CARLOS","CHEFE");
        Funcionario funcionario = new Funcionario("JOÃO","CHEFE");

        ReservaEquipamento reservaEquipamento = new ReservaEquipamento("2019-10-10",equipamento,funcionario);
        ReservaEspaco reservaEspaco = new ReservaEspaco("2019-11-11",espaco,funcionario1);

        EquipamentoModel equipamentoModel = new EquipamentoModel();
        EquipamentoView equipamentoView = new EquipamentoView();
        EquipamentoController equipamentoController = new EquipamentoController(equipamentoModel,equipamentoView);
        EspacoView espacoView = new EspacoView();
        EspacoModel espacoModel = new EspacoModel();
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

        //INSERINDO DADOS:
        equipamentoController.InserirEquipamentos(equipamentoModel,equipamento);
        espacoController.InserirEspaco(espacoModel,espaco);
        funcionarioController.InserirFuncionario(funcionarioModel,funcionario);
        funcionarioController.InserirFuncionario(funcionarioModel,funcionario1);
        reservaEquipamentoController.InserirReservaEquipamento(reservaEquipamentoModel,reservaEquipamento);
        reservaEspacoController.InserirReservaEspaco(reservaEspacoModel,reservaEspaco);

        //LISTANDO TODOS OS DADOS:
        equipamentoController.ListarEquipamentos(equipamentoModel);
        espacoController.ListarEspaco(espacoModel);
        funcionarioController.ListarFuncionario(funcionarioModel);
        reservaEquipamentoController.ListarReservaEquipamento(reservaEquipamentoModel);
        reservaEspacoController.ListarReservaEspaco(reservaEspacoModel);
         //LISTAR POR NOME:
        equipamentoController.BuscarPorNomeEquipamento(equipamentoModel);
        espacoController.BuscarPorNomeEspaco(espacoModel);
        funcionarioController.BuscarPorNomeFuncionario(funcionarioModel);
        reservaEquipamentoController.BuscarPorNomeFuncionario(reservaEquipamentoModel);
        reservaEspacoController.BuscarPorNomeFuncionario(reservaEspacoModel);
        //LISTAR POR DATA:
        reservaEquipamentoController.BuscarPorDataReservaEquipamento(reservaEquipamentoModel);
        reservaEspacoController.BuscarPorDataReservaEspaco(reservaEspacoModel);
        //EXCLUIR POR ID:
        reservaEquipamentoController.ExcluirReservaEquipamento(reservaEquipamentoModel);
        reservaEspacoController.ExcluirReservaEspaco(reservaEspacoModel);
    }

}
