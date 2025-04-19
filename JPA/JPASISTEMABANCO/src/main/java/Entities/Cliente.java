package Entities;

import javax.persistence.*;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cliente;
    @Column
    private String nome;
    @Column
    private String telefone;

    public Cliente(String Nome,String Telefone){
        this.nome = Nome;
        this.telefone = Telefone;
    }

    public Cliente() {

    }

    public String getTelefone() {
        return telefone;
    }

    public String getNome() {
        return nome;
    }

    public int getId_cliente() {
        return id_cliente;
    }

}
