/**
 * 
 */
package com.salesianostriana.dam.gestiapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@NotNull
	@ManyToOne
	private Room reservedRoom;
	@NotNull
	@ManyToOne
	private AppUser reserveUser;
	@NotNull
	@ManyToOne
	private ReservedDate date;
	@NotNull
	@ManyToOne
	private TimeZone timeZone;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	private School school;
	
	/**
	 * @param id           Id de la reserva
	 * @param reservedRoom Sala reservada
	 * @param reserveUser  Usuario reservante
	 * @param date         Fecha de la reserva
	 * @param timeZone     Tramo horario de la reserva
	 */
	public Reserve(long id, @NotNull Room reservedRoom, @NotNull AppUser reserveUser, @NotNull ReservedDate date,
			@NotNull TimeZone timeZone) {
		super();
		this.id = id;
		this.reservedRoom = reservedRoom;
		this.reserveUser = reserveUser;
		this.date = date;
		this.timeZone = timeZone;
	}

}
