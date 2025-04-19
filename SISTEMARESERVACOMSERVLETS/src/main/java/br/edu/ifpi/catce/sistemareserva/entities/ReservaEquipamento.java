package br.edu.ifpi.catce.sistemareserva.entities;

import javax.persistence.*;
@Entity
public class ReservaEquipamento extends Reserva{
    @ManyToOne
    @JoinColumn(name = "id_equipamento", referencedColumnName = "id_equipamento")
    private Equipamento equipamento;

    public ReservaEquipamento(String data, Equipamento equipamento,Funcionario funcionario) {
        super(data,funcionario);
        this.equipamento = equipamento;
    }

    public ReservaEquipamento() {
        super();
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

}
