package com.example.application.services;

import com.example.application.data.Location;
import com.example.application.data.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public List<Location> getAllLocations() {
        return repository.findAll();
    }

    public Location saveLocation(Location location) {
        return repository.save(location);
    }

    public void deleteLocation(Long id) {
        repository.deleteById(id);
    }
}
