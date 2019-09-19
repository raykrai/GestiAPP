/**
 * 
 */
package com.salesianostriana.dam.gestiapp.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
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
	private Boolean locked;

	/**
	 * @param id     Id de fecha reservada.
	 * @param date   Fecha reservada.
	 * @param locked Booleano que define si esa fecha esta bloqueada o no para la
	 *               reserva.
	 */
	public ReservedDate(long id, @NotNull LocalDate date, Boolean locked) {
		super();
		this.id = id;
		this.date = date;
		this.locked = locked;
	}

}
