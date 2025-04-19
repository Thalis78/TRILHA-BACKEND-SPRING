package Utils;

public class ReservaEspaco extends Reserva{
    private int id_espaco;
    public ReservaEspaco(int id, String data, int id_funcionario, int id_espaco){
        super(id,data,id_funcionario);
        this.id_espaco = id_espaco;
    }

    public int getId_espaco() {
        return id_espaco;
    }

    public void setId_espaco(int id_espaco) {
        this.id_espaco = id_espaco;
    }
}
