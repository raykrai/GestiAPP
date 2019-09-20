package com.salesianostriana.dam.gestiapp.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestiapp.model.Room;
import com.salesianostriana.dam.gestiapp.repository.RoomRepository;
import com.salesianostriana.dam.gestiapp.service.base.BaseService;

@Service
public class RoomService extends BaseService<Room, Long, RoomRepository> {

}
