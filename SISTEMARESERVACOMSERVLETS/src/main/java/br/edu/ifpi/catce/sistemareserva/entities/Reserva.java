package br.edu.ifpi.catce.sistemareserva.entities;

import javax.persistence.*;
//AS ANOTAÇÕES EM JAVA SÃO METADADOS USADA PARA DESCREVER E ESPECIFICAR CARACTERISTICAS ADICIONAIS SOBRE ELEMENTOS DO CÓDIGO, COMO CLASSES, MÉTODOS, CAMPOS OU PARÂMETROS.
//AS ANOTAÇÕES SÃO UMA FORMA DE ACRESCENTAR INFORMAÇÕES ADICIONAIS AO CÓDIGO FONTE;
@Entity
public abstract class Reserva {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_reserva;
    private String dt_reserva;
    @ManyToOne
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id")
    private Funcionario funcionario;

    public Reserva(String data,Funcionario funcionario) {
        this.dt_reserva = data;
        this.funcionario =funcionario ;
    }

    public Reserva() {

    }

    public String getDt_reserva() {
        return dt_reserva;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }
    public int getId_reserva() {
        return id_reserva;
    }

}





