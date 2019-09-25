/**
 * 
 */
package com.salesianostriana.dam.gestiapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestiapp.model.ReserveChecker;
import com.salesianostriana.dam.gestiapp.model.ReservedDate;
import com.salesianostriana.dam.gestiapp.repository.ReserveCheckerRepository;
import com.salesianostriana.dam.gestiapp.service.base.BaseService;

/**
 * @author jmbargueno
 *
 */

@Service
public class ReserveCheckerService extends BaseService<ReserveChecker, Long, ReserveCheckerRepository> {

	@Autowired
	ReserveCheckerRepository reserveCheckerRepository;

	@Autowired
	ReservedDateService reservedDateService;

	@Autowired
	ReserveService reserveService;

	@Autowired
	ReservedDateService reserveDateService;
	

	public Boolean checkWeekend(LocalDateTime localDateTime) {
		int dayOfWeek = localDateTime.getDayOfWeek().getValue();

		// Si el dia es sabado o domingo
		if (dayOfWeek == 6 && dayOfWeek == 7) {
			// Si el fin de semana esta activo
			if (this.findAll().get(0).getWeekendOn() == true) {
				return true;
			} else {
				return false;
			}
		}
		return true;

	}

	public Boolean checkReservedDate(LocalDateTime localDateTime) {

		List<ReservedDate> reservedDateList = reserveDateService.findAll();

		for (ReservedDate reservedDate : reservedDateList) {

		}

		return null;

	}
}
