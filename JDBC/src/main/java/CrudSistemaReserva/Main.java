package CrudSistemaReserva;

import SistemaReserva.*;

import java.sql.SQLException;

public class Main{
    public static void main(String[] args) throws SQLException {
        //INSTACIANDO AS MINHAS CLASSES:
        Equipamento equipamento = new Equipamento(1,"EQUIPAMENTO01",50);
        Espaco espaco = new Espaco(1,"ESPACO01","A");
        Funcionario funcionario = new Funcionario(1,"JOAO","CARGO");
        ReservaEquipamento reservaEquipamento = new ReservaEquipamento(1,"2024-10-10",funcionario.getId(),equipamento.getId());
        ReservaEspaco reservaEspaco = new ReservaEspaco(1,"2024-10-11",funcionario.getId(),espaco.getId());

        CrudEquipamento crudEquipamento = new CrudEquipamento();
        CrudEspaco crudEspaco = new CrudEspaco();
        CrudFuncionario crudFuncionario = new CrudFuncionario();
        CrudReservaEquipamento crudReservaEquipamento = new CrudReservaEquipamento();
        CrudReservaEspaco crudReservaEspaco = new CrudReservaEspaco();
        //INSERINDO DADOS PARA TABELA:
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>INSERIR DADOS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        crudEquipamento.inserir(equipamento);
        crudEspaco.inserir(espaco);
        crudFuncionario.inserir(funcionario);
        crudReservaEquipamento.inserir(reservaEquipamento);
        crudReservaEspaco.inserir(reservaEspaco);
        //LISTAR TODOS:
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>LISTAR TODOS OS DADOS<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        crudEquipamento.buscarTodos();
        crudEspaco.buscarTodos();
        crudFuncionario.buscarTodos();
        crudReservaEspaco.buscarTodos();
        crudReservaEquipamento.buscarTodos();
        //EXCLUIR:
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>EXCLUIR DADOS<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        crudEquipamento.excluir();
        crudEspaco.excluir();
        crudFuncionario.excluir();
        crudReservaEquipamento.excluir();
        crudReservaEspaco.excluir();
        //LISTAR POR NOME:
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>LISTAR DADOS PELO NOME<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        crudEquipamento.buscarPorNome();
        crudEspaco.buscarPorNome();
        crudFuncionario.buscarPorNome();
        crudReservaEquipamento.buscarPorNomeFuncionario();
        crudReservaEspaco.buscarPorNomeFuncionario();
        //LISTAR POR DATA:
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>LISTAR DADOS PELA DATA<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        crudReservaEspaco.buscarPorData();
        crudReservaEquipamento.buscarPorData();
    }
}
