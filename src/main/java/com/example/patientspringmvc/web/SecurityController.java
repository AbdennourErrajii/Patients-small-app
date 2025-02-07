package com.example.patientspringmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/403")
    public String notAuthorized() {
        return "403"; // Retourne le nom de la vue (par exemple, un fichier 403.html dans templates)
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Retourne le nom de la vue (par exemple, un fichier login.html dans templates)
    }
}
