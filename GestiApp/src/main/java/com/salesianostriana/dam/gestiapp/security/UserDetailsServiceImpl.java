/**
 * 
 */
package com.salesianostriana.dam.gestiapp.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestiapp.service.UserService;

/**
 * Clase que implementa el usuario de seguridad
 * 
 * @author jmbargueno
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	UserService userService;

	public UserDetailsServiceImpl(UserService servicio) {
		this.userService = servicio;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userService.searchByUsername(username);

		UserBuilder userBuilder = null;

		if (user != null) {
			userBuilder = User.withUsername(user.getUsername());
			userBuilder.disabled(false);
			userBuilder.password(user.getPassword());
			if (user.isAdmin()) {
				// Este caso indica que un ADMIN también puede hacer todo lo que hace un USER
				userBuilder.authorities(new SimpleGrantedAuthority("ROLE_USER"),
						new SimpleGrantedAuthority("ROLE_ADMIN"));
			} else {
				userBuilder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
			}
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}

		return userBuilder.build();

	}

}