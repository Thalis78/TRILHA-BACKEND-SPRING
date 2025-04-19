public class ClienteFisico extends Cliente{
    private String Cpf;
    public ClienteFisico(String Cpf, String Nome,String Telefone){
        super(Nome,Telefone);
        this.Cpf = Cpf;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }
}
