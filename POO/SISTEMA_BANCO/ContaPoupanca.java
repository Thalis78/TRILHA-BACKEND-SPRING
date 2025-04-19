public class ContaPoupanca extends Conta {
    private double SaldoPoupanca;
    public ContaPoupanca(String Agencia, String Conta, Cliente cliente,double SaldoPoupanca){
        super(Agencia,Conta,cliente);
        this.SaldoPoupanca = SaldoPoupanca;
    }

    public double getSaldoPoupanca() {
        return SaldoPoupanca;
    }

    public void setSaldoPoupanca(double saldoPoupanca) {
        SaldoPoupanca = saldoPoupanca;
    }
    public void ResultadoMenu(double saldo){
        int Resultadomenu = EscolhaUsuario();
        switch (Resultadomenu){
            case 1:
                Deposito(saldo);
                this.SaldoPoupanca += saldo;
            case 2:
                Saque(saldo);
                this.SaldoPoupanca -= saldo;
            case 3:
                System.out.println("|---> SEU SALDO: R$" + saldo);
            case 0:
                System.out.println("PROGRAMA FINALIZADO");
        }
    }
}
