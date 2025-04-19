package br.edu.ifpi.catce.sistemareserva.model;

import jakarta.persistence.*;
@Entity
@Table(name = "EQUIPAMENTO")
public class EquipamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_equipamento;
    private String nomeEquipamento;
    private int quantidade_disponivel;

    public Integer getId_equipamento() {
        return id_equipamento;
    }

    public void setId_equipamento(Integer id_equipamento) {
        this.id_equipamento = id_equipamento;
    }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
    }

    public int getQuantidade_disponivel() {
        return quantidade_disponivel;
    }

    public void setQuantidade_disponivel(int quantidade_disponivel) {
        this.quantidade_disponivel = quantidade_disponivel;
    }
}

