package com.salesianostriana.dam.gestiapp.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.gestiapp.model.TimeZone;
import com.salesianostriana.dam.gestiapp.repository.TimeZoneRepository;
import com.salesianostriana.dam.gestiapp.service.base.BaseService;

@Service
public class TimeZoneService extends BaseService<TimeZone, Long, TimeZoneRepository> {

}
