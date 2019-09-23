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
 * Clase modelo de aula.
 * 
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
	
	@ManyToOne
	private School school;
	
	@ManyToOne
	private RoomCategory roomCategory;

	/**
	 * 
	 * @param id      Id de la sala reservada.
	 * @param name    Nombre de la sala reservada.
	 * @param roomNum Numero del aula de escuela.
	 * @param school  Escuela a la que le pertenece el aula.
	 * @param roomCategory Categoría del aula.
	 */
	public Room(long id, @NotNull String name, @NotNull int roomNum, School school, RoomCategory roomCategory) {
		super();
		this.id = id;
		this.name = name;
		this.roomNum = roomNum;
		this.school = school;
		this.roomCategory = roomCategory;
	}
	
	

	
	

}
