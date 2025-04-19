import java.util.Scanner;

public class Reserva {
    Scanner input = new Scanner(System.in);
    private int id;
    private String dt_reserva;
    private String nome_pessoa;
    private String escolha_item;

    public Scanner getInput() {
        return input;
    }
    public Reserva(int id, String data, String nome){
        this.id = id;
        this.dt_reserva = data;
        this.nome_pessoa = nome;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDt_reserva() {
        return dt_reserva;
    }

    public void setDt_reserva(String dt_reserva) {
        this.dt_reserva = dt_reserva;
    }

    public String getNome_pessoa() {
        return nome_pessoa;
    }

    public void setNome_pessoa(String nome_pessoa) {
        this.nome_pessoa = nome_pessoa;
    }


    public void setInput(Scanner input) {
        this.input = input;
    }

    public static void reserva(Reserva reserva, Funcionario funcionario){
        System.out.println(
                "\nID DA RESERVA: " + reserva.getId()+
                        "\nDATA DA RESERVA: " + reserva.getDt_reserva()+
                        "\nNOME: " + funcionario.getNome()
        );
    }
    public static String item(int id, String descr){
        String item_reserva =
                "\nID DO ITEM DA RESERVA : " + id +
                        "\nDESCRIÇÃO DO ITEM DA RESERVA: " + descr;
        return item_reserva;
    }
    public static void sistema_email(String funcionario, String item,String email_func){
        System.out.println(
                "\n------------- RESERVA REALIZADA COM SUCESSO -------------"+
                "\nEMAIL: "+  email_func+
                "\nMENSAGEM: "+
                "A RESERVA FOI REALIZADA POR " + funcionario + " E O ITEM RESERVADO FOI O " + item);
    }


    public void reserva(Reserva reserva, Funcionario funcionario, String item) {
    }
}
