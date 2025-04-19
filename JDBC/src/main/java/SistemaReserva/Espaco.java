package SistemaReserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Espaco {
    Scanner scanner = new Scanner(System.in);
    private int id;
    private String nome_espaco;
    private String status;

    public Espaco(int id, String nome_espaco, String status){
        this.id = id;
        this.nome_espaco = nome_espaco;
        this.status = status;
    }
    public int getId() {
        return id;
    }

    public String getNome_espaco() {
        return nome_espaco;
    }

    public String getStatus() {
        return status;
    }

    public static void informacao_espaco(Espaco espaco){
        System.out.println(
                "ID DO ESPAÇO: " + espaco.getId()+
                        "\nNOME DO ESPAÇO: " + espaco.getNome_espaco()+
                        "\nSTATUS DO ESPAÇO: " + espaco.getStatus() +
                        "\nESPACO INSERIDO COM SUCESSO\n"

        );
    }
}
