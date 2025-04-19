import java.awt.*;
import java.util.Scanner;

public abstract class Conta {
    private String Agencia;
    private String Conta;
    private String NomeTitular;

    static Scanner scanner = new Scanner(System.in);



    public Conta(String Agencia, String Conta, Cliente cliente){
        this.Agencia = Agencia;
        this.Conta = Conta;
        this.NomeTitular = cliente.getNome();

    }
    public String getAgencia() {
        return Agencia;
    }

    public void setAgencia(String agencia) {
        Agencia = agencia;
    }

    public String getConta() {
        return Conta;
    }

    public void setConta(String conta) {
        Conta = conta;
    }

    public String getNomeTitular() {
        return NomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        NomeTitular = nomeTitular;
    }

    public static int Menu(){
        System.out.println(
                ">>>>>>>>>>>>>>>>>> MENU <<<<<<<<<<<<<<<<" +
                "\n(1) - DEPOSITO" +
                "\n(2) - SAQUE "+
                "\n(3) - CONSULTAR SALDO"+
                "\n(0) - ENCERRAR MENU" +
                "\nESCOLHA: ");
        return scanner.nextInt();
    }
    public static int EscolhaUsuario(){
        int menu = Menu();
        while (menu > 4){
            menu =Menu();
        }
        return menu;
    }

    public static double Deposito(double Saldo){
        System.out.println("QUANTO VOCÊ DESEJA DEPOSITAR? R$");

        double deposito = scanner.nextDouble();
        double Novosaldo = deposito + Saldo;

        System.out.println("DEPOSITO REALIZADO COM SUCESSO!!!!");
        System.out.println("NOVO SALDO: R$" + Novosaldo);

        return Novosaldo;
    }
    public static double Saque(double Saldo) {
        System.out.println("QUANTO VOCÊ DESEJA SACAR? R$");
        double saque = scanner.nextDouble();
        double NovoSaldo;
        if (saque <= Saldo) {
            NovoSaldo = Saldo - saque;
            System.out.println(
                    "|---> SAQUE REALIZADO: R$" + saque +
                            "\n|---> NOVO SALDO: R$" + (NovoSaldo));
            return NovoSaldo;
        } else {
            System.out.println("SAQUE NEGADO!! SALDO MENOR QUE O SAQUE!!! TENTE NOVAMENTE");
            Saque(Saldo);
        }
        NovoSaldo = Saldo;
        return NovoSaldo;
    }
    public static void ContaCriada(Conta conta, String identificar,double saldo){
        System.out.println(
                ">>>>>>>>>>>>>>>>>>>>>>CONTA CRIADA COM SUCESSO <<<<<<<<<<<<<<<<<<<<" +
                "\n|---> AGENCIA                 :" + conta.getAgencia() +
                "\n|---> CONTA                   :" + conta.getConta() +
                "\n|---> NOME TITULAR            :" + conta.getNomeTitular() +
                "\n|---> IDENTIFICAÇÃO(CPF/CNPJ) :" + identificar +
                "\n|--->SALDO INICIAL            : R$"+ saldo);
    }
}
