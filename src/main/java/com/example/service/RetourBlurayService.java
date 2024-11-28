
package com.example.service;

import com.example.entities.RetourBluray;
import com.example.repository.RetourBlurayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetourBlurayService {

    private final RetourBlurayRepository repository;

    public RetourBlurayService(RetourBlurayRepository repository) {
        this.repository = repository;
    }

    public List<RetourBluray> getAll() {
        return repository.findAll();
    }

    public RetourBluray getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public RetourBluray save(RetourBluray entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<RetourBluray> findLateReturns() {
        return repository.findLateReturns();
    }
    
}