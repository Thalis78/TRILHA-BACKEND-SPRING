package br.edu.ifpi.catce.sistemareserva.model;
import jakarta.persistence.*;
@Entity
public class ReservaEspacoModel extends ReservaModel{
    @ManyToOne
    @JoinColumn(name = "id_espaco", referencedColumnName = "id_espaco")
    private EspacoModel espaco;

    public ReservaEspacoModel(){
        super();
    }

    public EspacoModel getEspaco() {
        return espaco;
    }

    public void setEspaco(EspacoModel espaco) {
        this.espaco = espaco;
    }
}