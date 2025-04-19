import java.util.Scanner;

public abstract class Funcionario {
    static Scanner input = new Scanner(System.in);
    private int id;
    private String nome;
    private String email_profissional;
    private String cargo;

    public Funcionario(int id, String nome, String email_profissional,String cargo){
        this.id = id;
        this.nome = nome;
        this.email_profissional = email_profissional;
        this.cargo = cargo;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail_profissional() {
        return email_profissional;
    }

    public void setEmail_profissional(String email_profissional) {
        this.email_profissional = email_profissional;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public void informacao_funcionario(){
        System.out.println(
                "\nID DO FUNCIONARIO:" + id +
                "\nNOME DO FUNCIONARIO: " + nome +
                "\nCARGO DO FUNCIONARIO: " + cargo
        );
    }
    public static void solicitar_reserva(Reserva reserva, Funcionario funcionario, Equipamento equipamento){
        reserva.reserva(reserva,funcionario);
        reserva.item(equipamento.getId(),equipamento.getDescri());
        reserva.sistema_email(funcionario.getNome(),equipamento.getDescri(),funcionario.getEmail_profissional());
    }

}
