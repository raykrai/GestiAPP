/**
 * 
 */
package com.salesianostriana.dam.gestiapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase modelo de usuario.
 * 
 * @author jmbargueno
 *
 */
@Data
@NoArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name, surname;

	@NotNull
	@Column(unique = true)
	private String userEmail;

	@NotNull
	private String password;

	private Boolean admin, validated;

	/**
	 * @param id        Id del usuario.
	 * @param name      Nombre del usuario.
	 * @param surname   Apellidos del usuario.
	 * @param userEmail Correo electronico del usuario.
	 * @param password  Password del usuario.
	 * @param admin     Booleano que define si el usuario es administrador o no.
	 * @param validated Booleando que define si el usuario esta validado para el uso
	 *                  de la plataforma o no.
	 */
	public User(long id, String name, String surname, @NotNull String userEmail, @NotNull String password,
			Boolean admin, Boolean validated) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.userEmail = userEmail;
		this.password = password;
		this.admin = admin;
		this.validated = validated;
	}

}
