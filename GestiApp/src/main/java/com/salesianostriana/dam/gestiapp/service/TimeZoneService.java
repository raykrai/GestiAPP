/**
 * 
 */
package com.salesianostriana.dam.gestiapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestiapp.model.TimeZone;
import com.salesianostriana.dam.gestiapp.repository.TimeZoneRepository;
import com.salesianostriana.dam.gestiapp.service.base.BaseService;

/**
 * @author jmbargueno
 *
 */
@Service
public class TimeZoneService extends BaseService<TimeZone, Long, TimeZoneRepository> {
	
	@Override
	public List<TimeZone> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

}
