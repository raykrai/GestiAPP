package com.salesianostriana.dam.gestiapp.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.salesianostriana.dam.gestiapp.model.AppUser;
import com.salesianostriana.dam.gestiapp.service.AppUserService;

/**
 * 
 * @author jmrlaguna
 *
 */

@Controller
public class AdminController {
	
	@Autowired
	private AppUserService userService;
	
	@GetMapping ("/admin/validate")
	public String showMenu(Model model) {
		
		List<AppUser> users = userService.searchByValitadedFalse();
        model.addAttribute("users", users);
		return "userstovalidate";
	}
	
	
}
