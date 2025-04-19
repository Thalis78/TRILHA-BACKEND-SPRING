package Entities;

import javax.persistence.*;
@Entity
public class ReservaEspaco extends Reserva{
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_espaco", referencedColumnName = "id_espaco")
    private Espaco espaco;

    public ReservaEspaco(String data, Espaco espaco,Funcionario funcionario) {
        super(data,funcionario);
        this.espaco = espaco;
    }

    public ReservaEspaco() {

    }

    public Espaco getEspaco() {
        return espaco;
    }

    public static void reserva(ReservaEspaco reservaEspaco) {
        System.out.println(
                "\nID DA RESERVA: " + reservaEspaco.getId_reserva() +
                        "\nDATA DA RESERVA: " + reservaEspaco.getDt_reserva() +
                        "\nID DO FUNCIONARIO: " + reservaEspaco.getFuncionario().getId() +
                        "\nID DO ITEM DO PEDIDO: " +  reservaEspaco.getEspaco().getId_espaco()+"\n"
        );
    }
}
