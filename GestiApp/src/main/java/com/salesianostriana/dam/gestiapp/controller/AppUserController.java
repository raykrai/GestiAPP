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
//@WebServlet("/profile")
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
	public String showFormRegisterSubmit(@ModelAttribute("userFormBean") UserFormBean userFormBean,
			BCryptPasswordEncoder passwordEncoder) {

		AppUser u = new AppUser();

		u.setName(userFormBean.getName());
		u.setSurname(userFormBean.getSurname());
		u.setUserEmail(userFormBean.getUserEmail());
		u.setPassword(passwordEncoder.encode(userFormBean.getPassword()));
		u.setAdmin(userFormBean.getAdmin());
		u.setValidated(false);
		u.setSchool(userFormBean.getSchool());

		appUserService.save(u);

		return "redirect:/";
	}

	/*
	 * @GetMapping("/profile") public String showFormProfileUser(Model model) {
	 * 
	 * model.addAttribute("userFormBean", new UserFormBean());
	 * 
	 * return "userProfile";
	 * 
	 * }
	 */

	/*
	 * Coge el id de un profesor
	 * 
	 * @GetMapping("/profile/{id}") public String
	 * showFormProfileUser(@PathVariable("id") long id, Model model) {
	 * 
	 * AppUser u = appUserService.findById(id); model.addAttribute("userP", u);
	 * 
	 * return "userProfile";
	 * 
	 * }
	 */

	/*
	 * @GetMapping("/profile") public String showFormProfileUser (HttpServletRequest
	 * request, HttpServletResponse response, Model model) throws ServletException,
	 * IOException {
	 * 
	 * HttpSession misession = (HttpSession) request.getSession();
	 * 
	 * AppUser miuser = (AppUser) misession.getAttribute("user");
	 * 
	 * AppUser u = new AppUser();
	 * 
	 * u.setName(miuser.getName()); u.setSurname(miuser.getSurname());
	 * u.setUserEmail(miuser.getUserEmail()); u.setSchool(miuser.getSchool());
	 * 
	 * model.addAttribute("user", u);
	 * 
	 * return "userProfile";
	 * 
	 * }
	 */

	@GetMapping("/profile")
	public String showFormProfile(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		User user = (User) auth.getPrincipal();
		AppUser appUser = appUserService.searchByEmail(user.getUsername());

		model.addAttribute("userFormBean", new UserFormBean());
		model.addAttribute("userProfile", appUser);

		return "formUserProfile";
	}
	
	@GetMapping("/profileTable")
	public String profileTable(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		User user = (User) auth.getPrincipal();
		AppUser appUser = appUserService.searchByEmail(user.getUsername());

		model.addAttribute("userProfile", appUser);

		return "userProfile";
	}


	@GetMapping("/profile/{id}")
	public String FormValidatedEdit(@PathVariable("id") long id, Model model) {

		AppUser appUser = appUserService.findById(id);
		
		model.addAttribute("usuario", appUser);
		
		return "redirect:/profile";
	}
	
	@PostMapping("/profile/submit")
	public String FormValidatedSubmit(@ModelAttribute("userFormBean") UserFormBean userFormBean) {
		
		AppUser apuser = new AppUser();
		
		apuser.setName(userFormBean.getName());
		apuser.setSurname(userFormBean.getSurname());
		apuser.setUserEmail(userFormBean.getUserEmail());
		appUserService.edit(apuser);
		
		return "redirect:/profile";
		
	}

}
