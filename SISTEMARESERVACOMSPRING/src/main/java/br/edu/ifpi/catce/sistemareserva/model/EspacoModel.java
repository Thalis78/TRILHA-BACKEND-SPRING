package br.edu.ifpi.catce.sistemareserva.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name = "ESPACO")
public class EspacoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_espaco;
    @NotEmpty
    private String nomeEspaco;
    private String status;

    public Integer getId_espaco() {
        return id_espaco;
    }

    public void setId_espaco(Integer id_espaco) {
        this.id_espaco = id_espaco;
    }

    public String getNomeEspaco() {
        return nomeEspaco;
    }

    public void setNomeEspaco(String nomeEspaco) {
        this.nomeEspaco = nomeEspaco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}