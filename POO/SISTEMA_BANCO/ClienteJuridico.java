public class ClienteJuridico extends Cliente{
    private String Cnpj;
    public ClienteJuridico(String Cnpj,String Nome,String Telefone){
        super(Nome,Telefone);
        this.Cnpj = Cnpj;
    }

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String cnpj) {
        Cnpj = cnpj;
    }

}
