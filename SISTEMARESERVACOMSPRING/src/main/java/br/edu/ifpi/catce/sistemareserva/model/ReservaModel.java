package br.edu.ifpi.catce.sistemareserva.model;

import jakarta.persistence.*;

@Entity
@Table(name = "RESERVA")
public abstract class ReservaModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_reserva;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id")
    private FuncionarioModel funcionario;

    public FuncionarioModel getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioModel funcionario) {
        this.funcionario = funcionario;
    }

    public int getId_reserva() {
        return id_reserva;
    }
}
