import java.util.List;

public class ProgramacaoFuncional {

    public void pecorrerListaViaForEach(List<?> lista){
        lista.stream().
                forEach(n -> System.out.println(n));
    }

    public void transformarListaViaMap(List<Integer> numeros){
        List<Integer> dobrados = numeros.stream()
                        .map(x  ->  x*2)
                        .toList();

        pecorrerListaViaForEach(dobrados);
    }

    public void filtrarParesViaFilter(List<Integer> numeros){
        List<Integer> pares = numeros.stream()
                        .filter(x -> x % 2 == 0)
                        .toList();

        pecorrerListaViaForEach(pares);
    }

    public void converteNomesParaMaiusculo(List<String> nomes){
        List<String> listaDeNomes = nomes.stream()
                                         .map(String::toUpperCase)
                                         .toList();

        pecorrerListaViaForEach(listaDeNomes);
    }

    public void converteNomesparaMinusculo(List<String> nomes){
        List<String> listaDeNomes = nomes.stream()
                .map(String::toLowerCase)
                .toList();

        pecorrerListaViaForEach(listaDeNomes);
    }

}
