package br.edu.ifpi.catce.sistemareserva.entities;

import javax.persistence.*;
@Entity
public class ReservaEspaco extends Reserva{
    @ManyToOne
    @JoinColumn(name = "id_espaco", referencedColumnName = "id_espaco")
    private Espaco espaco;

    public ReservaEspaco(String data, Espaco espaco,Funcionario funcionario) {
        super(data,funcionario);
        this.espaco = espaco;
    }

    public ReservaEspaco() {

    }

    public Espaco getEspaco() {
        return espaco;
    }


}
