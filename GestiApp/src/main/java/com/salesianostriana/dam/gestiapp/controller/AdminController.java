package com.salesianostriana.dam.gestiapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String showFormValidated(Model model) {

		model.addAttribute("users", userService.searchByValitadedFalse());
		return "userstovalidate";
	}
	
	//Coge la id de un usuario en el botón "Validar"
	@GetMapping("/admin/validate/{id}")
	public String FormValidatedEdit(@PathVariable("id") long id, Model model) {
		
		boolean validate = true;
		AppUser u = userService.findById(id);
		
		if(u != null) {
			
			//Set de true a un usuario y lo guarda
			model.addAttribute("user", u);
			u.setValidated(validate);
			userService.save(u);
			
			return "redirect:/admin/validate";//le paso el formulario para editar usuarios
			
		}else {
			
			return "redirect:/admin/validate";
		}
	}
	
	@PostMapping("/admin/validate/submit")
	public String FormValidatedSubmit(@ModelAttribute("user") AppUser u) {
		
		boolean validate = true;
		
		u.setValidated(validate);
		userService.save(u);
		
		return "redirect:/admin/validate";
		
	}
		
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
