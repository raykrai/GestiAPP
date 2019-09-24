package com.salesianostriana.dam.gestiapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.gestiapp.formbeans.UserFormBean;
import com.salesianostriana.dam.gestiapp.model.AppUser;
import com.salesianostriana.dam.gestiapp.service.AppUserService;
import com.salesianostriana.dam.gestiapp.service.SchoolService;

@Controller
public class AppUserController {

	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private SchoolService schoolService;

	@GetMapping("/formRegister")
	public String showFormRegister(Model model) {

		model.addAttribute("userFormBean", new UserFormBean());
		model.addAttribute("listSchool", schoolService.findAll());

		return "register";

	}

	@PostMapping("/formRegister/submit")
	public String showFormRegisterSubmit(@ModelAttribute("userFormBean") UserFormBean userFormBean, BCryptPasswordEncoder passwordEncoder) {

		AppUser u = new AppUser();

		u.setName(userFormBean.getName());
		u.setSurname(userFormBean.getSurname());
		u.setUserEmail(userFormBean.getUserEmail());
		u.setPassword(passwordEncoder.encode(userFormBean.getPassword()));
		u.setAdmin(false);
		u.setValidated(false);
		u.setSchool(userFormBean.getSchool());

		appUserService.save(u);

		return "redirect:/";
	}
	
	//Busca el usuario de la sesion actual por el email y lo añadimos como atributo al modelo
	@GetMapping("/profile")
	public String showFormProfile(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		User user = (User) auth.getPrincipal();
		AppUser appUser = appUserService.searchByEmail(user.getUsername());

		model.addAttribute("userProfile", appUser);

		return "formProfileShow";
	}
	
	//Busca usuario por id y pilla el de userProfile que es el usuario de la session, guarda en un atributo el objeto encontrado y 
	//lo mando como atributo profileEdit al POST y el servicio edita el usuario de la session actual
	@GetMapping("/profile/{id}")
	public String showFormProfileEdit(@ModelAttribute("userProfile") AppUser u, Model model) {
		
		AppUser pEdit = appUserService.findById(u.getId());
		
		if(pEdit != null) {
			
			model.addAttribute("profileEdit", pEdit);
			
			return "formProfileEdit";//le paso el formulario de editar productos
			
		}else {
			
			return "redirect:/profile";
		}
		
	}
	
	//Guarda en la base de datos el usuario con su nueva edición
	@PostMapping("/profile/submit")
	public String processFormProfileEdit(@ModelAttribute("profileEdit") AppUser puser, Model model) {
		
		appUserService.edit(puser);
		
		return "redirect:/profile";
	}

}
