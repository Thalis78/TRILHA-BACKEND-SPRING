package CrudSistemaReserva;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> TESTE DE SQL INJECTION <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        ExemploInjecaoSql exemploInjecao = new ExemploInjecaoSql();
        exemploInjecao.buscarPorNomeVulneravel();
        exemploInjecao.buscarPorNomeSeguro();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> FIM DO TESTE DE SEGURANÇA <<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}