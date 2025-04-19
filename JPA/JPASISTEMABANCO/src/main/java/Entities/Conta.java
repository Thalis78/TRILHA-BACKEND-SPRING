package Entities;

import javax.persistence.*;

@Entity
public class Conta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_conta;
    @Column
    private String Agencia;
    @Column
    private String Conta;
    @Column
    private String tipoConta;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente id_cliente;
    public Conta(String Agencia, String Conta,String tipoConta,Cliente cliente){
        this.Agencia = Agencia;
        this.Conta = Conta;
        this.tipoConta = tipoConta;
        this.id_cliente = cliente;

    }

    public Conta() {

    }

    public int getId_conta() {
        return id_conta;
    }

    public String getAgencia() {
        return Agencia;
    }

    public String getConta() {
        return Conta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public Cliente getId_cliente() {
        return id_cliente;
    }

}
