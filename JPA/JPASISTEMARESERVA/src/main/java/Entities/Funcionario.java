package Entities;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.Scanner;

@Entity
public class Funcionario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nome_funcionario;
    @Column
    private String cargo;

    public Funcionario(String nome,String cargo){
        this.nome_funcionario = nome;
        this.cargo = cargo;
    }

    public Funcionario() {

    }

    public int getId() {
        return id;
    }

    public String getNome_funcionario() {
        return nome_funcionario;
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
                "\nNOME DO FUNCIONARIO: " + funcionario.getNome_funcionario() +
                "\nCARGO DO FUNCIONARIO: " + funcionario.getCargo() +
                "\nFUNCIONARIO INSERIDO COM SUCESSO"
        );
    }
}
