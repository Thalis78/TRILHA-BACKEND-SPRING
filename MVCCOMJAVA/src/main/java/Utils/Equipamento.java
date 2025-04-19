package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Equipamento {
    static Scanner scanner = new Scanner(System.in);
    private int id;
    private String nome_equipamento;
    private int quantidade_disponivel;

    public Equipamento(int id, String nome_equipamento, int quantidade_disponivel){
        this.id = id;
        this.nome_equipamento = nome_equipamento;
        this.quantidade_disponivel = quantidade_disponivel;
    }
    public int getId() {
        return id;
    }

    public String getNome_equipamento(){return nome_equipamento;}

    public int getQuantidade_disponivel() {
        return quantidade_disponivel;
    }

    public static void informarcao_equipamento(Equipamento equipamento){
        System.out.println("ID DO EQUIPAMENTO: " + equipamento.getId() +
                "\nNOME DO EQUIPAMENTO: " + equipamento.getNome_equipamento() +
                "\nQUANTIDADE DISPONÍVEL: " + equipamento.getQuantidade_disponivel()+
                "\nEQUIPAMENTO INSERIDO COM SUCESSO!!!!\n");

    }
}
