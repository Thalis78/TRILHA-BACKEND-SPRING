import java.util.Scanner;

public class Espaco {
    private int id;
    private String descri;
    private String status;

    public Espaco(int id, String descri, String status){
        this.id = id;
        this.descri = descri;
        this.status = status;
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


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;}

    public static void informacao_espaco(int id, String descr, String status){
        System.out.println(
                "ID DO ESPAÇO: " + id+
                        "\nDESCRIÇÃO DO ESPAÇO: " + descr+
                        "\nSTATUS DO ESPAÇO: " + status
        );
    }

}
