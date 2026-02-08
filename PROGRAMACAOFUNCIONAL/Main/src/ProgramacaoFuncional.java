import java.util.Comparator;
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
    public void removerDuplicatas(List<String> nomes){
        List<String> listaNova  = nomes.stream()
                .distinct()
                .toList();

        pecorrerListaViaForEach(listaNova);
    }

    public void ordenarListaDesc(List<String> nomes){
        List<String> listaNova = nomes.stream()
                .sorted(Comparator.reverseOrder())
                .toList();

        pecorrerListaViaForEach(listaNova);
    }

    public void ordenarListaAsc(List<String> nomes){
        List<String> listaNova = nomes.stream()
                .sorted()
                .toList();

        pecorrerListaViaForEach(listaNova);
    }

    public void limitarQunatidadeLista(List<String> nomes, Integer quantidadeNaLista){
        List<String> listaNova = nomes.stream()
                .limit(quantidadeNaLista)
                .toList();

        pecorrerListaViaForEach(listaNova);
    }

public void contarItensLista(List<String> nomes){
    System.out.println(nomes.stream().count());
}
public void reduzirTudoPraUmUnicoValor(List<Integer> numeros){
        int soma = numeros.stream()
                .reduce(20, Integer::sum  );

    System.out.println(soma);
}
}
