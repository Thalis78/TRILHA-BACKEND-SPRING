package Controllers;


import Model.EspacoModel;
import Entities.Espaco;
import View.EspacoView;

import java.sql.SQLException;
import java.util.List;

public class EspacoController {
    private EspacoModel espacoModel;
    private EspacoView espacoView;

    public EspacoController(EspacoModel espacoModel, EspacoView espacoView) {
        this.espacoModel = espacoModel;
        this.espacoView = espacoView;
    }
    public void BuscarPorNomeEspaco(EspacoModel espacoModel) throws SQLException{
        espacoModel.buscarPorNome();
    }
    public void ListarEspaco(EspacoModel espacoModel){
        List<Espaco> espacos = espacoModel.buscarTodos();
        if(espacos.isEmpty()){
            System.out.println("NENHUM EQUIPAMENTO REGISTRADO!!!");
        }else {
            espacoView.ListaEspaco(espacos);
        }
    }
    public void InserirEspaco(EspacoModel espacoModel, Espaco espaco) throws SQLException {
        espacoModel.inserir(espaco);
        espaco.informacao_espaco(espaco);
        System.out.println("DADOS INSERIDOS COM SUCESSO!!!!");
    };
    public void ExcluirEspaco(EspacoModel espacoModel) throws SQLException{
        espacoModel.excluir();
        System.out.println("EXCLUIDO COM SUCESSO!!!");
    }
}
