package InterfaceSistemaReserva;

import SistemaReserva.Equipamento;
import SistemaReserva.Espaco;
import SistemaReserva.Funcionario;

import java.sql.SQLException;
import java.util.List;

public interface FuncionarioDao {

    Funcionario buscarPorNome() throws SQLException;

    List<Funcionario> buscarTodos();

    public void inserir(Funcionario funcionario) throws SQLException;

    public void excluir() throws SQLException;

}
