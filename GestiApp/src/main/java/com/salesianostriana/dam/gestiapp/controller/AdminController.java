package com.salesianostriana.dam.gestiapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.salesianostriana.dam.gestiapp.formbeans.RoomFormBean;
import com.salesianostriana.dam.gestiapp.formbeans.UserFormBean;
import com.salesianostriana.dam.gestiapp.model.AppUser;
import com.salesianostriana.dam.gestiapp.model.Pager;
import com.salesianostriana.dam.gestiapp.model.Reserve;
import com.salesianostriana.dam.gestiapp.model.Room;
import com.salesianostriana.dam.gestiapp.model.RoomCategory;
import com.salesianostriana.dam.gestiapp.service.AppUserService;
import com.salesianostriana.dam.gestiapp.service.ReserveService;
import com.salesianostriana.dam.gestiapp.service.RoomCategoryService;
import com.salesianostriana.dam.gestiapp.service.RoomService;
import com.salesianostriana.dam.gestiapp.service.SchoolService;

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
	private static final int[] PAGE_SIZES = { 5, 10, 20, 50 };

	@Autowired
	private AppUserService userService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private RoomCategoryService roomCategoryService;
	
	@Autowired
	private ReserveService reserveService;
	
	/** VALIDACIÓN **/

	@GetMapping("/admin/validate")
	public String showFormValidated(Model model) {

		model.addAttribute("users", userService.searchByValitadedFalse());
		return "userstovalidate";
	}

	// Coge la id de un usuario en el botón "Validar".
	@GetMapping("/admin/validate/{id}")
	public String FormValidatedEdit(@PathVariable("id") long id, Model model) {

		boolean validate = true;
		AppUser u = userService.findById(id);

		if (u != null) {

			// Set de true a un usuario y lo guarda
			model.addAttribute("user", u);
			u.setValidated(validate);
			userService.save(u);

			return "redirect:/admin/validate";// le paso el formulario para editar usuarios

		} else {

			return "redirect:/admin/validate";
		}
	}

	/** LISTA DE USUARIOS **/

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

	@PostMapping("/admin/editUser/submit")
	public String editUserSubmit(@ModelAttribute("userFormBean") UserFormBean userFormBean,
			BCryptPasswordEncoder passwordEncoder) {

		AppUser u = userService.findById(userFormBean.getId());
//		u.setId(userFormBean.getId());
		u.setName(userFormBean.getName());
		u.setSurname(userFormBean.getSurname());
		u.setUserEmail(userFormBean.getUserEmail());
		u.setPassword(passwordEncoder.encode(userFormBean.getPassword()));
		u.setAdmin(userFormBean.getAdmin());
		u.setSchool(userFormBean.getSchool());

		userService.edit(u);

		return "redirect:/admin/users";
	}

	@GetMapping("/admin/editUser/{id}")
	public String editUser(@PathVariable("id") long id, Model model) {
		model.addAttribute("listSchool", schoolService.findAll());

		AppUser editedAppUser = userService.findById(id);

		if (editedAppUser != null) {
			model.addAttribute("userFormBean", editedAppUser);
			return "editUser";
		} else {
			return "redirect:/admin/users";
		}

	}

/** CALENDARIO **/

	@GetMapping("/admin/calendar")
	public String calendar(Model model) {
		return "calendar";
	}

/** LISTA DE AULAS **/

	@GetMapping("/admin/rooms")
	public String showRooms(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, Model model) {

		// Evalúa el tamaño de página. Si el parámetro es "nulo", devuelve
		// el tamaño de página inicial.
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);

		// Calcula qué página se va a mostrar. Si el parámetro es "nulo" o menor
		// que 0, se devuelve el valor inicial. De otro modo, se devuelve el valor
		// del parámetro decrementado en 1.
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		// Obtenemos la página definida por evalPage y evalPageSize de objetos de
		// nuestro modelo
		Page<Room> rooms = roomService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		// Creamos el objeto Pager (paginador) indicando los valores correspondientes.
		// Este sirve para que la plantilla sepa cuantas páginas hay en total, cuantos
		// botones
		// debe mostrar y cuál es el número de objetos a dibujar.
		Pager pager = new Pager(rooms.getTotalPages(), rooms.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("rooms", rooms);
		model.addAttribute("room", new Room());
		model.addAttribute("roomCategories", roomCategoryService.findAll());
		model.addAttribute("schools", schoolService.findAll());
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);
		return "roomAdministration";
	}

	@GetMapping("/admin/delRoom/{id}")
	public String delRooms(@PathVariable("id") long id, Model model) {
		roomService.deleteById(id);
		model.addAttribute("rooms", roomService.findAll());
		return "redirect:/admin/rooms";
	}

	@GetMapping("/admin/editRoom/{id}")
	public String editRoom(@PathVariable("id") long id, Model model) {

		model.addAttribute("room", roomService.findById(id));
		model.addAttribute("roomCategories", roomCategoryService.findAll());
		model.addAttribute("schools", schoolService.findAll());
		return "formRoomEdit";

	}

	@PostMapping("/admin/editRoom/submit")
	public String editRoomSubmit(@ModelAttribute("room") Room room) {

		roomService.edit(room);
		return "redirect:/admin/rooms";
	}
	
	@PostMapping("/admin/roomSave")
	public String addCerveza(@ModelAttribute("room") Room room, Model model) {
		
		roomService.save(room);
		
		return "redirect:/admin/rooms";
	}
/** LISTA DE CATEGORÍAS **/
	
	@GetMapping("/admin/roomCategories")
	public String roomCategory(Model model) {

		List<RoomCategory> categories = roomCategoryService.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("category", new RoomCategory());
		return "roomCategories";
	}
	
	@GetMapping("/admin/editCategory/{id}")
	public String editCategory(@PathVariable("id") long id, Model model) {

		RoomCategory editCategory = roomCategoryService.findById(id);
		model.addAttribute("category", editCategory);
		
		return "roomCategoryEdit";
	}
	
	@PostMapping("/admin/categorySubmit")
	public String procesarCerveza(@ModelAttribute("category") RoomCategory c) {
		roomCategoryService.edit(c);
		return "redirect:/admin/roomCategories";
	}
	
	@GetMapping("/admin/categoryDel/{id}")
	public String roomCategoryDel(@PathVariable("id") long id) {
		roomCategoryService.deleteById(id);
		return "redirect:/admin/roomCategories";
	}
	
	@PostMapping("/admin/categorySave")
	public String addCerveza(@ModelAttribute("category") RoomCategory category, Model model) {
		RoomCategory catego = new RoomCategory();
		
		catego.setCategoryName(category.getCategoryName());
		
		roomCategoryService.save(catego);
		
		return "redirect:/admin/roomCategories";
	}
/** RESERVAS **/
	@GetMapping("/admin/reserves")
	public String showReserves(Model model) {

		List<Reserve> reserves = reserveService.findAll();
		model.addAttribute("reserves", reserves);
		return "reserveAdministration";
	}
	
	@GetMapping("/admin/reserveDel/{id}")
	public String reserveDel(@PathVariable("id") long id) {
		reserveService.deleteById(id);
		return "redirect:/admin/reserves";
	}
}
