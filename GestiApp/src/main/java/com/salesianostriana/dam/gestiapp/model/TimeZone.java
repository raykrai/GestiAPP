/**
 * 
 */
package com.salesianostriana.dam.gestiapp.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	@Setter(AccessLevel.NONE)
	private long id;

	@NotNull
	private String name;

	@NotNull
	private LocalTime time;

	/**
	 * 
	 * @param id   Id del tramo horario.
	 * @param name Nombre del tramo horario.
	 * @param time Time del tramo horario.
	 */
	public TimeZone(long id, @NotNull String name, @NotNull LocalTime time) {
		super();
		this.id = id;
		this.name = name;
		this.time = time;
	}

}
