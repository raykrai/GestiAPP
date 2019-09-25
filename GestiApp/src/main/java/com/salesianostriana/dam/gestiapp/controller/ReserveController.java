package com.salesianostriana.dam.gestiapp.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.gestiapp.formbeans.ReserveFormBean;
import com.salesianostriana.dam.gestiapp.model.Reserve;
import com.salesianostriana.dam.gestiapp.model.RoomCategory;
import com.salesianostriana.dam.gestiapp.model.TimeZone;
import com.salesianostriana.dam.gestiapp.service.ReserveService;
import com.salesianostriana.dam.gestiapp.service.RoomCategoryService;
import com.salesianostriana.dam.gestiapp.service.TimeZoneService;


@Controller
public class ReserveController {

	@Autowired
	private ReserveService reserveService;
	@Autowired
	private TimeZoneService timeZoneService;
	@Autowired
	private RoomCategoryService roomCategoryService;

	// Cambiar la ruta del mapping con la plantilla del formulario en cuestión , LA
	// QUE ESTÁ PUESTA ES DE EJEMPLO - 22/09/2019.

	@GetMapping("/formReserve")
	public String showFormReserve(Model model) {
		List<TimeZone> timeZoneList= timeZoneService.findAll();
		List<RoomCategory> categories = roomCategoryService.findAll();
		
		model.addAttribute("timeZoneList", timeZoneList);
		model.addAttribute("categories", categories);
		model.addAttribute("reserveFormBean", new ReserveFormBean());

		return "formReserve";

	}

	// Cambiar la ruta del mapping con la plantilla del formulario en cuestión , LA
	// QUE ESTÁ PUESTA ES DE EJEMPLO - 22/09/2019.

	@PostMapping("/formReserve/submit")
	public String submitFormReserve(@ModelAttribute("reserveFormBean") ReserveFormBean reserveFormBean) {

		Reserve r = new Reserve();

		r.setReservedRoom(reserveFormBean.getReservedRoom());
		r.setReserveUser(reserveFormBean.getReserveUser());
		r.setDate(reserveFormBean.getDate());
		r.setTimeZone(reserveFormBean.getTimeZone());

		reserveService.save(r);

		return "vistaReserva";
	}

}
