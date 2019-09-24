package com.salesianostriana.dam.gestiapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestiapp.model.RoomCategory;
import com.salesianostriana.dam.gestiapp.repository.RoomCategoryRepository;
import com.salesianostriana.dam.gestiapp.service.base.BaseService;

@Service
public class RoomCategoryService extends BaseService<RoomCategory, Long, RoomCategoryRepository> {
	
	@Autowired
	private RoomCategoryRepository roomCategoryRepository;
	
	@Override
	public RoomCategory save(RoomCategory t) {
		// TODO Auto-generated method stub
		return super.save(t);
	}

	@Override
	public RoomCategory edit(RoomCategory t) {
		// TODO Auto-generated method stub
		return super.edit(t);
	}

	@Override
	public void delete(RoomCategory t) {
		// TODO Auto-generated method stub
		super.delete(t);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		super.deleteById(id);
	}

	@Override
	public RoomCategory findById(Long id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<RoomCategory> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
}
