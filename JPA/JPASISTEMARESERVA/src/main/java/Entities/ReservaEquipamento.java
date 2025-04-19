package Entities;

import javax.persistence.*;
@Entity
public class ReservaEquipamento extends Reserva{
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_equipamento", referencedColumnName = "id_equipamento")
    private Equipamento equipamento;

    public ReservaEquipamento(String data, Equipamento equipamento,Funcionario funcionario) {
        super(data,funcionario);
        this.equipamento = equipamento;
    }

    public ReservaEquipamento() {
        super();
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public static void reserva(ReservaEquipamento reservaEquipamento) {
        System.out.println(
                "\nID DA RESERVA: " + reservaEquipamento.getId_reserva() +
                        "\nDATA DA RESERVA: " + reservaEquipamento.getDt_reserva() +
                        "\nID DO FUNCIONARIO: " + reservaEquipamento.getFuncionario().getId() +
                        "\nID DO ITEM DO PEDIDO: " +  reservaEquipamento.getEquipamento().getId_equipamento()+"\n"
        );
    }
}
