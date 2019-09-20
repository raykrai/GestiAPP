/**
 * 
 */
package com.salesianostriana.dam.gestiapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase modelo de las reservas.
 * 
 * @author jmbargueno
 *
 */

@Data
@NoArgsConstructor
@Entity
public class Reserve {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	@ManyToOne
	private Room reservedRoom;
	@NotNull
	@ManyToOne
	private User reserveUser;
	@NotNull
	private ReservedDate date;
	@NotNull
	private TimeZone timeZone;

	/**
	 * @param id           Id de la reserva
	 * @param reservedRoom Sala reservada
	 * @param reserveUser  Usuario reservante
	 * @param date         Fecha de la reserva
	 * @param timeZone     Tramo horario de la reserva
	 */
	public Reserve(long id, @NotNull Room reservedRoom, @NotNull User reserveUser, @NotNull ReservedDate date,
			@NotNull TimeZone timeZone) {
		super();
		this.id = id;
		this.reservedRoom = reservedRoom;
		this.reserveUser = reserveUser;
		this.date = date;
		this.timeZone = timeZone;
	}

}
