package Entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
@Entity
public abstract class Reserva {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_reserva;
    @Column
    private String dt_reserva;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id")
    private Funcionario funcionario;

    public Reserva(String data,Funcionario funcionario) {
        this.dt_reserva = data;
        this.funcionario = funcionario;
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





