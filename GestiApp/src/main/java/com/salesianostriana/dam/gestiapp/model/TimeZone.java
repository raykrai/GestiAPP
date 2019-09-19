/**
 * 
 */
package com.salesianostriana.dam.gestiapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jmbargueno
 *
 */

@Data
@NoArgsConstructor
@Entity
public class TimeZone {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Column(unique = true)
	private String name;

	@NotNull
	private int duration;

	/**
	 * @param id       Id del tramo horario.
	 * @param name     Nombre del tramo horario (Como primera hora diurna, segunda
	 *                 hora nocturna, etc).
	 * @param duration Duracion del tramo horario.
	 */
	public TimeZone(long id, @NotNull String name, @NotNull int duration) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
	}

}
