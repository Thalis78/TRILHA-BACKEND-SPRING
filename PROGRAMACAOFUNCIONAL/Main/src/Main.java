import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<Integer> numeros = List.of(1,3,4,5,6);
        List<String>  nomes   = List.of("Thalisson","Viana","Moura");

        ProgramacaoFuncional programacaoFuncional = new ProgramacaoFuncional();

        System.out.println("------------- FOR EACH ----------------");
        programacaoFuncional.pecorrerListaViaForEach(numeros);
        System.out.println("------------- MAP ----------------------");
        programacaoFuncional.transformarListaViaMap(numeros);
        System.out.println("------------- FILTER -------------------");
        programacaoFuncional.filtrarParesViaFilter(numeros);
        System.out.println("------------- MAIUSCULO -------------------");
        programacaoFuncional.converteNomesParaMaiusculo(nomes);
        System.out.println("------------- MINUSCULO -------------------");
        programacaoFuncional.converteNomesparaMinusculo(nomes);
    }
}