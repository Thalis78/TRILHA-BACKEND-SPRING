package br.edu.ifpi.catce.sistemareserva.interfacesistema;

import br.edu.ifpi.catce.sistemareserva.entities.Equipamento;

import java.sql.SQLException;
import java.util.List;

//PROJETO DAO É UM PADRÃO DE PROJETO BASEADO EM INTERFACE, ELE TEM COMO FINALIDADE DE GARANTIR QUE TODOS OS METODOS SEJAM IMPLEMENTADOS DE MANEIRA OBRIGATORIA.
public interface EquipamentoDao {

    String buscarPorNome(String nome) throws SQLException;

    List<Equipamento> buscarTodos();

    public void inserir(Equipamento equipamento) throws SQLException;

}
