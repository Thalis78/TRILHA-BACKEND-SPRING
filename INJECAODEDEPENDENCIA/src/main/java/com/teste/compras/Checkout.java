package com.teste.compras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component é usado para referenciar as classes que o Spring deve criar o @Bean(Instanciar o objeto)
@Component
@Scope("prototype")
public class Checkout {
    //Autowired é usado para referênciar um componente de um objeto que já foi instanciado pelo spring
    @Autowired
    //Qualifier é usado para especificar o tipo de objeto que está sendo instanciado, caso exista mais de um;
    @Qualifier("impressora")
    private Impressora impressora;
    public Checkout(Impressora impressora) {
        this.impressora = impressora;
    }

    public void finalizar(){
        this.impressora.imprimir("texto");
    }
}
