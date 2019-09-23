package com.salesianostriana.dam.gestiapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @author jmrlaguna
 *
 */


@Controller
public class WelcomeController {
	
	@GetMapping("/")
    public String cargarWeb() {
        return "index";
    }
}
