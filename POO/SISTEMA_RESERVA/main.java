import java.util.Scanner;

public class main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args){
        Equipamento equipamento = new Equipamento(11,"Equipamento01",100,50);
        Espaco espaco = new Espaco(12,"Espaço01","Ativo");
        FuncionarioNormal func01 = new FuncionarioNormal(1,"JUNIOR","junior13@gmail.com","NORMAL");
        Chefia chefia = new Chefia(1,"THALISSON","thalissonmoura138@gmail.com","CHEFIA");

        Reserva reserva = new Reserva(101,"31/03/2024",func01.getNome());
        Reserva reserva1 = new Reserva(102,"31/03/2024",chefia.getNome());

        System.out.println(">>>>>>>>>>>>FUNCIONARIOS<<<<<<<<<<<<<<<");
        func01.informacao_funcionario();
        chefia.informacao_funcionario();

        System.out.println("\n>>>>>>>>>>>>>>>RESERVA<<<<<<<<<<<<<<<<<");
        func01.solicitar_reserva(reserva,func01,equipamento);
        chefia.solicitar_reserva(reserva1,chefia,espaco);




    }



}
