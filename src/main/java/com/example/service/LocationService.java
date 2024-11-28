
package com.example.service;

import com.example.entities.Location;
import com.example.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public List<Location> getAll() {
        return repository.findAll();
    }

    public Location getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Location save(Location entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Double calculateTariff(Long locationId) {
        return repository.calculateTariff(locationId);
    }
    
}