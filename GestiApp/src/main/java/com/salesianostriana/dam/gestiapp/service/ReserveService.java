package com.salesianostriana.dam.gestiapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestiapp.model.Reserve;
import com.salesianostriana.dam.gestiapp.repository.ReserveRepository;
import com.salesianostriana.dam.gestiapp.service.base.BaseService;

@Service
public class ReserveService extends BaseService<Reserve, Long, ReserveRepository> {

	@Autowired
	ReserveRepository reserveRepository;
	
	public List<Reserve> findAllByReserveUserId(long id){
		return reserveRepository.findAllByReserveUserId(id);
	}
	
}
