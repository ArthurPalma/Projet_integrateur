
package com.example.service;

import com.example.entities.CarteAbonnement;
import com.example.repository.CarteAbonnementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteAbonnementService {

    private final CarteAbonnementRepository repository;

    public CarteAbonnementService(CarteAbonnementRepository repository) {
        this.repository = repository;
    }

    public List<CarteAbonnement> getAll() {
        return repository.findAll();
    }

    public CarteAbonnement getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public CarteAbonnement save(CarteAbonnement entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    
    public List<CarteAbonnement> findByControlParental(Boolean controlParental) {
        return repository.findByControlParental(controlParental);
    }
    
    public void updateSolde(Long id, Double newSolde) {
        repository.updateSolde(id, newSolde);
    }
    
}