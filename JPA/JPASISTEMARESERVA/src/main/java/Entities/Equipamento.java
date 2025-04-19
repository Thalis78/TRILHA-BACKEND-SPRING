package Entities;

import javax.persistence.*;

@Entity
public class Equipamento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_equipamento;
    @Column
    private String nome_equipamento;
    @Column(name = "quantidade_disponivel")
    private int quantidade_disponivel;

    public Equipamento(String nome_equipamento, int quantidade_disponivel){
        this.nome_equipamento = nome_equipamento;
        this.quantidade_disponivel = quantidade_disponivel;
    }

    public Equipamento() {

    }

    public int getId_equipamento() {
        return id_equipamento;
    }

    public void setId_equipamento(int id_equipamento) {
        this.id_equipamento = id_equipamento;
    }

    public String getNome_equipamento() {
        return nome_equipamento;
    }

    public void setNome_equipamento(String nome_equipamento) {
        this.nome_equipamento = nome_equipamento;
    }

    public int getQuantidade_disponivel() {
        return quantidade_disponivel;
    }

    public void setQuantidade_disponivel(int quantidade_disponivel) {
        this.quantidade_disponivel = quantidade_disponivel;
    }

    public static void informarcao_equipamento(Equipamento equipamento){
        System.out.println("ID DO EQUIPAMENTO: " + equipamento.getId_equipamento() +
                "\nNOME DO EQUIPAMENTO: " + equipamento.getNome_equipamento() +
                "\nQUANTIDADE DISPONÍVEL: " + equipamento.getQuantidade_disponivel()+
                "\nEQUIPAMENTO INSERIDO COM SUCESSO!!!!\n");

    }

}
