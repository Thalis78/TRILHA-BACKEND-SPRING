package SistemaReserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Funcionario {
    static Scanner input = new Scanner(System.in);
    private int id;
    private String nome;
    private String cargo;

    public Funcionario(int id, String nome,String cargo){
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
    }
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public static void informacao_funcionario(Funcionario funcionario){
        System.out.println(
                "\nID DO FUNCIONARIO:" + funcionario.getId() +
                "\nNOME DO FUNCIONARIO: " + funcionario.getNome() +
                "\nCARGO DO FUNCIONARIO: " + funcionario.getCargo() +
                "\nFUNCIONARIO INSERIDO COM SUCESSO"
        );
    }
}
