package Controllers;

import Model.EspacoModel;
import Model.FuncionarioModel;
import Entities.Funcionario;
import View.FuncionarioView;

import java.sql.SQLException;
import java.util.List;

public class FuncionarioController {
    private FuncionarioModel funcionarioModel;
    private FuncionarioView funcionarioView;

    public FuncionarioController(FuncionarioModel funcionarioModel, FuncionarioView funcionarioView){
        this.funcionarioModel = funcionarioModel;
        this.funcionarioView  = funcionarioView;
    }
    public void BuscarPorNomeFuncionario(FuncionarioModel funcionarioModel)throws SQLException{
        funcionarioModel.buscarPorNome();
    }
    public void ListarFuncionario(FuncionarioModel funcionarioModel){
        List<Funcionario> funcionarios = funcionarioModel.buscarTodos();
        if(funcionarios.isEmpty()){
            System.out.println("NENHUM EQUIPAMENTO REGISTRADO!!!");
        }else {
            funcionarioView.ListaFuncionario(funcionarios);
        }
    }
    public void InserirFuncionario(FuncionarioModel funcionarioModel, Funcionario funcionario) throws SQLException {
        funcionarioModel.inserir(funcionario);
        funcionario.informacao_funcionario(funcionario);
        System.out.println("DADOS INSERIDOS COM SUCESSO!!!");
    }
    public void ExcluirFuncionario(FuncionarioModel funcionarioModel) throws SQLException {
        funcionarioModel.excluir();
        System.out.println("EXCLUIDO COM SUCESSO!!!");
    }
}
