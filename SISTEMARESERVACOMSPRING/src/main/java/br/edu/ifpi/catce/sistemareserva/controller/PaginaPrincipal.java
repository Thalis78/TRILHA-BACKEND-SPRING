package br.edu.ifpi.catce.sistemareserva.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PaginaPrincipal {

    @GetMapping("/Paginaprincipal")
    public String initPagPrincipal(){
        return "index";
    }
}
