import java.util.Scanner;

public class Equipamento {
    private int id;
    private String descri;
    private int quantidade_total;
    private int quantidade_disponivel;

    public Equipamento(int id, String descri, int quantidade_total, int quantidade_disponivel){
        this.id = id;
        this.descri = descri;
        this.quantidade_total = quantidade_total;
        this.quantidade_disponivel = quantidade_disponivel;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public int getQuantidade_total() {
        return quantidade_total;
    }
    public void setQuantidade_total(int quantidade_total){
        this.quantidade_total = quantidade_total;
    }
    public int getQuantidade_disponivel() {
        return quantidade_disponivel;
    }
    public void setQuantidade_disponivel(int quantidade_disponivel){
        this.quantidade_disponivel = quantidade_disponivel;
    }
    public static void informarcao_equipamento(int id, String descr, int quantidade_disponivel){
        System.out.println("ID DO EQUIPAMENTO: " + id +
                "\nDESCRIÇÃO DO EQUIPAMENTO: " + descr +
                "\nQUANTIDADE DISPONÍVEL: " + quantidade_disponivel);

    }


}
