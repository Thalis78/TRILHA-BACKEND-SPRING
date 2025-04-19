package br.edu.ifpi.catce.sistemareserva.entities;


import javax.persistence.*;

@Entity
public class Espaco {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_espaco;
    @Column(name = "nome_Espaco")
    private String nome_espaco;
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


}
