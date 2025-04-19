package InterfaceSistemaReserva;

import SistemaReserva.Equipamento;
import SistemaReserva.Espaco;

import java.sql.SQLException;
import java.util.List;

public interface EspacoDao {


        Espaco buscarPorNome() throws SQLException;

        List<Espaco> buscarTodos();

        public void inserir(Espaco espaco) throws SQLException;

        public void excluir() throws SQLException;



}
