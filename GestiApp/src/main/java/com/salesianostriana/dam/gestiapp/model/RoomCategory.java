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
public class RoomCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private String categoryName;
	
	/**
	 * 
	 * @param id Id de la categoría
	 * @param categoryName Nombre de la categoría
	 */
	public RoomCategory(long id, @NotNull String categoryName) {
		super();
		this.id = id;
		this.categoryName = categoryName;
	}
	

	
	
}
