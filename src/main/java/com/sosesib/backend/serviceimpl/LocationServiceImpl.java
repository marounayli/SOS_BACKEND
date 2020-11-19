package com.sosesib.backend.serviceimpl;

import com.sosesib.backend.models.entities.Location;
import com.sosesib.backend.repositories.LocationRepository;
import com.sosesib.backend.services.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}
