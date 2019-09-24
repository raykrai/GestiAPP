package com.salesianostriana.dam.gestiapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.gestiapp.model.AppUser;
import com.salesianostriana.dam.gestiapp.model.Room;
import com.salesianostriana.dam.gestiapp.service.AppUserService;
import com.salesianostriana.dam.gestiapp.service.RoomService;
import com.salesianostriana.dam.gestiapp.model.Pager;

/**
 * 
 * @author jmrlaguna
 *
 */

@Controller
public class AdminController {
	
	
	private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20, 50};
    
	@Autowired
	private AppUserService userService;
	
	@Autowired
	private RoomService roomService;
	
	
	@GetMapping("/admin/validate")
	public String showFormValidated(Model model) {

		model.addAttribute("users", userService.searchByValitadedFalse());
		return "userstovalidate";
	}
	
	//Coge la id de un usuario en el botón "Validar".
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

	@GetMapping("/admin/users")
	public String showUsers(Model model) {

		List<AppUser> users = userService.findAll();
		model.addAttribute("users", users);
		return "userAdministration";
	}

	@GetMapping("/admin/delUser/{id}")
	public String delUsers(@PathVariable("id") long id, Model model) {
		userService.deleteById(id);
		model.addAttribute("users", userService.findAll());
		return "redirect:/admin/users";
	}
	
	@GetMapping("/admin/calendar")
	public String calendar(Model model) {
		return "calendar";
	}
	
	@GetMapping("/admin/rooms")
	public String showRooms(@RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page, Model model){

		// Evalúa el tamaño de página. Si el parámetro es "nulo", devuelve
    	// el tamaño de página inicial.
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        
        // Calcula qué página se va a mostrar. Si el parámetro es "nulo" o menor
        // que 0, se devuelve el valor inicial. De otro modo, se devuelve el valor
        // del parámetro decrementado en 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        // Obtenemos la página definida por evalPage y evalPageSize de objetos de nuestro modelo
        Page<Room> rooms = roomService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
        // Creamos el objeto Pager (paginador) indicando los valores correspondientes.
        // Este sirve para que la plantilla sepa cuantas páginas hay en total, cuantos botones
        // debe mostrar y cuál es el número de objetos a dibujar.
        Pager pager = new Pager(rooms.getTotalPages(), rooms.getNumber(), BUTTONS_TO_SHOW);
        
        model.addAttribute("rooms", rooms);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);
		return "roomAdministration";
	}
	

}
