package br.edu.ifpi.catce.sistemareserva.entities;

import javax.persistence.*;

@Entity
public class Funcionario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome_funcionario;
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


}
