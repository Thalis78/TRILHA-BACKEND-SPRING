public class Main {
    public static void main(String[] args){
        ClienteFisico clienteFisico = new ClienteFisico("071000000-82","THALISSON","(86) 9999-9999");
        ClienteJuridico clienteJuridico = new ClienteJuridico("0000000000000000000-10","JUNIOR","(89)0000-0000");

        ContaPoupanca contaPoupanca = new ContaPoupanca("3500-0","00000-1",clienteFisico,100.00);
        ContaCorrente contaCorrente = new ContaCorrente("4000-0","0000-2",clienteJuridico,50.00);

        contaPoupanca.ContaCriada(contaPoupanca,clienteFisico.getCpf(),contaPoupanca.getSaldoPoupanca());
        contaCorrente.ContaCriada(contaCorrente,clienteJuridico.getCnpj(),contaCorrente.getSaldoCorrente());

        contaCorrente.ResultadoMenu(contaCorrente.getSaldoCorrente());
    }
}
