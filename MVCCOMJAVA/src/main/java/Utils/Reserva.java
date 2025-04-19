package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public abstract class Reserva {


    private int id;
    private String dt_reserva;
    private int id_funcionario;

    public Reserva(int id, String data, int id_funcionario) {
        this.id = id;
        this.dt_reserva = data;
        this.id_funcionario = id_funcionario;
    }

    public int getId() {
        return id;
    }

    public String getDt_reserva() {
        return dt_reserva;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }


    public static void reserva(Reserva reserva,int id_funcionario) {
        System.out.println(
                "\nID DA RESERVA: " + reserva.getId() +
                        "\nDATA DA RESERVA: " + reserva.getDt_reserva() +
                        "\nID DO FUNCIONARIO: " + reserva.getId_funcionario() +
                        "\nID DO ITEM DO PEDIDO: " + id_funcionario +"\n"
        );
    }}





