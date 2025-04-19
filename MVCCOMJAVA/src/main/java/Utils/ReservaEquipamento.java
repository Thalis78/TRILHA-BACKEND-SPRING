package Utils;

public class ReservaEquipamento extends Reserva{
    private int id_equipamento;
    public ReservaEquipamento(int id, String data, int id_funcionario, int id_equipamento){
        super(id,data,id_funcionario);
        this.id_equipamento = id_equipamento;
    }

    public int getId_equipamento() {
        return id_equipamento;
    }

    public void setId_equipamento(int id_equipamento) {
        this.id_equipamento = id_equipamento;
    }
}
