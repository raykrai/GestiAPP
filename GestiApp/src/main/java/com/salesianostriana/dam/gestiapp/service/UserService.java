package com.salesianostriana.dam.gestiapp.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestiapp.model.User;
import com.salesianostriana.dam.gestiapp.repository.UserRepository;
import com.salesianostriana.dam.gestiapp.serviceBase.BaseService;

@Service
public class UserService extends BaseService<User, Long, UserRepository> {

}
