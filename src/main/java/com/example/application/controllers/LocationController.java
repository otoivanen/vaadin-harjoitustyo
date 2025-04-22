package com.example.application.controllers;

import com.example.application.data.Location;
import com.example.application.services.LocationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Location> getLocations() {
        return service.getAllLocations();
    }

    @PostMapping
    public Location createLocation(@RequestBody Location location) {
        return service.saveLocation(location);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable Long id) {
        service.deleteLocation(id);
    }
}