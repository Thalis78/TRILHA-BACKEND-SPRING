package com.teste.compras;

import com.teste.compras.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//Injeção de dependência é basicamente você usufruir dependências de outras classes, evitando que eu crie coisas de maneira internas, permitindo flexibilidade no código e deixando ele modularizado
public class Main {
    public static void main(String[] args){
        //AplicationContext: é uma das injeçõees de dependência, e nela ele vai permitir usar instancias de objetos já criado por outras classes;
        //O Escopo do ApplicationContext é no formato singleton, ou seja, ele cria uma única instância durante todo o ciclo da aplicação;
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Checkout checkout = context.getBean(Checkout.class);
        checkout.finalizar();

        ((ConfigurableApplicationContext) context).close();
    }

}
