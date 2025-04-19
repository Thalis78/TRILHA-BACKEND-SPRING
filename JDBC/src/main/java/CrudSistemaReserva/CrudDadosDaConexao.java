package CrudSistemaReserva;

public class CrudDadosDaConexao {
    private String url = "jdbc:postgresql://localhost:5432/SistemaReserva";

    private String user = "postgres";

    private String password = "root";

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
