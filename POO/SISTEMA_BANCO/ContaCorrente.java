public class ContaCorrente extends Conta{
    private double SaldoCorrente;
    public ContaCorrente(String Agencia, String Conta, Cliente cliente,double SaldoCorrente){
        super(Agencia,Conta,cliente);
        this.SaldoCorrente = SaldoCorrente;
    }

    public double getSaldoCorrente() {
        return SaldoCorrente;
    }

    public void setSaldoCorrente(double saldoCorrente) {
        SaldoCorrente = saldoCorrente;
    }
    public void ResultadoMenu(double saldo){
        int Resultadomenu = EscolhaUsuario();
        switch (Resultadomenu){
            case 1:
                this.SaldoCorrente += Deposito(saldo);
                System.out.println("VOCÊ QUER REALIZAR OUTRAS CONSULTAS NO MENU? (1) - SIM ; (2) - NÃO");
                if(scanner.nextInt() == 1){
                    ResultadoMenu(this.SaldoCorrente);
                    break;
                }else{
                    System.out.println("PROGRAMA FINALIZADO!!!!");
                    break;
                }
            case 2:

                this.SaldoCorrente -= Saque(saldo);
                System.out.println("VOCÊ QUER REALIZAR OUTRAS CONSULTAS NO MENU? (1) - SIM ; (2) - NÃO");
                if(scanner.nextInt() == 1){
                    ResultadoMenu(this.SaldoCorrente);
                    break;
                }else{
                    System.out.println("PROGRAMA FINALIZADO!!!!");
                    break;
                }
            case 3:
                System.out.println("|---> SEU SALDO: R$" + saldo);
                break;
            case 0:
                System.out.println("PROGRAMA FINALIZADO");
                break;
        }
    }

}
