package br.edu.ifpi.catce.sistemareserva.interfacesistema;
import br.edu.ifpi.catce.sistemareserva.entities.Espaco;

import java.sql.SQLException;
import java.util.List;

public interface EspacoDao {


        String buscarPorNome(String nome) throws SQLException;

        List<Espaco> buscarTodos();

        public void inserir(Espaco espaco) throws SQLException;




}
