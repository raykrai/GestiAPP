package com.salesianostriana.dam.gestiapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/profile/{id}")
	public String showFormProfileEdit(@PathVariable("id") long id, Model model) {
		
		AppUser pEdit = appUserService.findById(id);
		
		if(pEdit != null) {
			
			model.addAttribute("profile", pEdit);
			
			return "formularioProfileEditar";//le paso el formulario de editar productos
			
		}else {
			
			return "redirect:/";
		}
		
	}
	
	@PostMapping("/profile/submit")
	public String processFormProfileEdit(@ModelAttribute("profile") AppUser puser, Model model) {
		
		appUserService.save(puser);
		
		return "redirect:/";
	}

}
