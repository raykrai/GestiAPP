/**
 * 
 */
package com.salesianostriana.dam.gestiapp.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestiapp.model.ReserveChecker;
import com.salesianostriana.dam.gestiapp.model.ReservedDate;
import com.salesianostriana.dam.gestiapp.model.TimeZone;
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

	// No usar este metodo, usar el del final
	public Boolean checkWeekend(LocalDate localDate) {
		int dayOfWeek = localDate.getDayOfWeek().getValue();

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

	// No usar este metodo, usar el del final
	public Boolean checkReservedDate(LocalDate localDate, TimeZone timeZone) {

		List<ReservedDate> reservedDateList = reserveDateService.findAll();

		boolean result = false;

		// Por cada reservedDate en la lista
		for (ReservedDate reservedDate : reservedDateList) {
			// Comprueba que la fecha dada tenga ya un reservedDate
			if (reservedDate.getDate().equals(localDate)) {
				// Si la fecha esta creada, comprueba que no este bloqueada
				if (reservedDate.getLocked() == false) {
					// Su no esta bloqueada, comprueba que no sea el mismo timeZone
					if (reservedDate.getTimeZone().equals(timeZone)) {
						result = false;
					} else {
						result = true;
					}
				} else {
					result = false;
				}

			} else {
				result = true;
			}

		}
		return result;

	}

	// Usar este metodo para comprobar si es posible la reserva o no.
	public Boolean checkDay(LocalDate localDate, TimeZone timeZone) {

		boolean result = false;
		LocalDate actualDate = LocalDate.now();
		LocalTime actualTime = LocalTime.now();

		// Si el dia que le pasamos no es anterior al de hoy
		if (!localDate.isBefore(actualDate)) {
			// Si estamos en el mismo dia, que compruebe la hora
			if (localDate.isEqual(actualDate)) {
				//Si la hora de la reserva no es anterior a la actual, entonces sigue el flujo
				if (!timeZone.getTime().isBefore(actualTime)) {
					//Si todo ok, chequea
					if (this.checkReservedDate(localDate, timeZone) && this.checkWeekend(localDate)) {
						result = true;
					}
				}

			} else {
				//Si no es el mismo dia, checkea
				if (this.checkReservedDate(localDate, timeZone) && this.checkWeekend(localDate)) {
					result = true;

				}
			}
		}

		return result;

	}
}
