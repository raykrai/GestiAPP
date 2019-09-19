/**
 * 
 */
package com.salesianostriana.dam.gestiapp.model;

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
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String name;

	@NotNull
	private int roomNum;

	/**
	 * @param id      Id de la sala reservada.
	 * @param name    Nombre de la sala reservada.
	 * @param roomNum Numero del aula de escuela.
	 */
	public Room(long id, @NotNull String name, @NotNull int roomNum) {
		super();
		this.id = id;
		this.name = name;
		this.roomNum = roomNum;
	}

}
