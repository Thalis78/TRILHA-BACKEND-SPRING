public class Chefia extends Funcionario {
    private String setor;
    public Chefia(int id, String nome, String email,String cargo){
        super(id,nome,email,cargo);
        this.setor = setor;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }


    public void informacao_funcionario() {
        System.out.println("INFORME O SETOR QUE VOCÊ CHEFIA: ");
        this.setor = input.next();
        super.informacao_funcionario();
        System.out.println("SETOR DA CHEFIA: " + setor);
    }

    public static void solicitar_reserva(Reserva reserva,Funcionario funcionario, Espaco espaco){
        reserva.reserva(reserva,funcionario);
        reserva.item(espaco.getId(),espaco.getDescri());
        reserva.sistema_email(funcionario.getNome(),espaco.getDescri(),funcionario.getEmail_profissional());
    }

}
