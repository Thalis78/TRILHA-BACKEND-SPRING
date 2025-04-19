package br.edu.ifpi.catce.sistemareserva.model;

import jakarta.persistence.*;

@Entity
public class ReservaEquipamentoModel extends ReservaModel {

    @ManyToOne
    @JoinColumn(name = "id_equipamento", referencedColumnName = "id_equipamento")
    private EquipamentoModel equipamento;

    public ReservaEquipamentoModel() {
        super();
    }

    public EquipamentoModel getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(EquipamentoModel equipamento) {
        this.equipamento = equipamento;
    }
}
