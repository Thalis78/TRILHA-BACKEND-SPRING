package br.edu.ifpi.catce.sistemareserva.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, RedirectAttributes redirectAttributes) {
        if (error != null) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro! Entre com um usuário e senha corretos.");
            redirectAttributes.addFlashAttribute("style", "alert alert-danger");
            return "redirect:/login";
        }
        return "login";
    }

}
