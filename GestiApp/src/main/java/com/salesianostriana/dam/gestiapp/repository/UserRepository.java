package com.salesianostriana.dam.gestiapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.gestiapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
