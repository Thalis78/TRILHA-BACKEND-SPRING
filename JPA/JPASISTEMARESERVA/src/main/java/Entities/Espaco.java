package Entities;


import javax.persistence.*;
import java.util.Scanner;
@Entity
public class Espaco {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_espaco;
    @Column(name = "nome_Espaco")
    private String nome_espaco;
    @Column
    private String status;

    public Espaco(String nome_espaco, String status){
        this.nome_espaco = nome_espaco;
        this.status = status;
    }

    public Espaco() {

    }

    public int getId_espaco() {
        return id_espaco;
    }

    public String getNome_espaco() {
        return nome_espaco;
    }

    public String getStatus() {
        return status;
    }

    public static void informacao_espaco(Espaco espaco){
        System.out.println(
                "ID DO ESPAÇO: " + espaco.getId_espaco()+
                        "\nNOME DO ESPAÇO: " + espaco.getNome_espaco()+
                        "\nSTATUS DO ESPAÇO: " + espaco.getStatus() +
                        "\nESPACO INSERIDO COM SUCESSO\n"

        );
    }
}
