/**
 * 
 */
package com.salesianostriana.dam.gestiapp.model;

import lombok.Data;

/**
 * @author jmbargueno
 *
 */
@Data
public class User {
	private String name, surname, userEmail, password;
	private Boolean admin, validated;
	private School school;
	
}
