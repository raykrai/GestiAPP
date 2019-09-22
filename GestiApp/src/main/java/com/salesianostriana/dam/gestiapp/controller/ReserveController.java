package com.salesianostriana.dam.gestiapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.gestiapp.formbeans.ReserveFormBean;
import com.salesianostriana.dam.gestiapp.model.Reserve;
import com.salesianostriana.dam.gestiapp.service.ReserveService;

@Controller
public class ReserveController {

	@Autowired
	private ReserveService reserveService;

	// Cambiar la ruta del mapping con la plantilla del formulario en cuestión , LA
	// QUE ESTÁ PUESTA ES DE EJEMPLO - 22/09/2019.

	@GetMapping("/formReserve")
	public String showFormReserve(Model model) {

		model.addAttribute("reserveFormBean", new ReserveFormBean());

		return "formularioReservas";

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
		r.setSchool(reserveFormBean.getSchool());

		reserveService.save(r);

		return "vistaReserva";
	}

}
