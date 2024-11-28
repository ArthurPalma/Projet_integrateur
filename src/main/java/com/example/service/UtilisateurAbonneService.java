
package com.example.service;

import com.example.entities.UtilisateurAbonne;
import com.example.repository.UtilisateurAbonneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurAbonneService {

    private final UtilisateurAbonneRepository repository;

    public UtilisateurAbonneService(UtilisateurAbonneRepository repository) {
        this.repository = repository;
    }

    public List<UtilisateurAbonne> getAll() {
        return repository.findAll();
    }

    public UtilisateurAbonne getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public UtilisateurAbonne save(UtilisateurAbonne entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<UtilisateurAbonne> findByNom(String nom) {
        return repository.findByNom(nom);
    }
    
    public void updateAdresse(Long id, String newAdresse) {
        repository.updateAdresse(id, newAdresse);
    }
    
}