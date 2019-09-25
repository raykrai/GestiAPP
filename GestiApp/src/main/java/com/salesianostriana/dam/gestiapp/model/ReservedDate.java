/**
 * 
 */
package com.salesianostriana.dam.gestiapp.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase modelo de fecha reservada, esta clase permite definir dias disponibles
 * para reservar o no las aulas
 * 
 * @author jmbargueno
 *
 */
@Data
@NoArgsConstructor
@Entity
public class ReservedDate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	@ManyToOne
	private TimeZone timeZone;

	@OneToMany(mappedBy = "date")
	private List<Reserve> reserves = new ArrayList<>();

	private Boolean locked;

	/**
	 * @param id       Id de fecha reservada.
	 * @param date     Fecha reservada.
	 * @param timeZone Tramo horario de la reserva
	 * @param locked   Booleano que define si esa fecha esta bloqueada o no para la
	 *                 reserva.
	 */
	public ReservedDate(long id, @NotNull LocalDate date, TimeZone timeZone, List<Reserve> reserves, Boolean locked) {
		super();
		this.id = id;
		this.date = date;
		this.timeZone = timeZone;
		this.reserves = reserves;
		this.locked = locked;
	}

}
