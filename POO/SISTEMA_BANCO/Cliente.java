public abstract class Cliente {
    private String Nome;
    private String Telefone;

    public Cliente(String Nome,String Telefone){
        this.Nome = Nome;
        this.Telefone = Telefone;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }


    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public static void ContaCriada(Cliente cliente,String Identificar){
        System.out.println(
                ">>>>>>>>>>>>>>>>>>> CLIENTE CRIADO COM SUCESSO <<<<<<<<<<<<<<<<<<<<<<<<<<<"
                +"\nNOME: "+ cliente.getNome()
                +"\nTELEFONE: "+ cliente.getTelefone()
                +"\nIDENTIFICAÇÃO(CPF/CNPJ):"+ Identificar
        );
    }

}
