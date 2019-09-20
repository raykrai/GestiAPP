package com.salesianostriana.dam.gestiapp.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestiapp.model.Reserve;
import com.salesianostriana.dam.gestiapp.repository.ReserveRepository;
import com.salesianostriana.dam.gestiapp.service.base.BaseService;

@Service
public class ReserveService extends BaseService<Reserve, Long, ReserveRepository> {

}
