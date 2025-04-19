package com.teste.compras;

import org.springframework.stereotype.Component;

@Component
public class Impressora implements InterfaceImpressora {
    @Override
    public void imprimir(String texto){
        System.out.println(texto);
    }
}
