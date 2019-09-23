package com.salesianostriana.dam.gestiapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

	@GetMapping("/admin/validate")
	public String showMenu(Model model) {

		List<AppUser> users = userService.searchByValitadedFalse();
		model.addAttribute("users", users);
		return "userstovalidate";
	}

	@GetMapping("/admin/users")
	public String showUsers(Model model) {

		List<AppUser> users = userService.findAll();
		model.addAttribute("users", users);
		return "userAdministration";
	}

	@GetMapping("/admin/delUser/{id}")
	public String delUsers(@PathVariable("id") long id, Model model) {
		model.addAttribute("user", userService.findAll());
		userService.deleteById(id);
		return "redirect:/admin/users";
	}

}
