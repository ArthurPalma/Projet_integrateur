
package com.example.service;

import com.example.entities.UtilisateurNonAbonne;
import com.example.repository.UtilisateurNonAbonneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurNonAbonneService {

    private final UtilisateurNonAbonneRepository repository;

    public UtilisateurNonAbonneService(UtilisateurNonAbonneRepository repository) {
        this.repository = repository;
    }

    public List<UtilisateurNonAbonne> getAll() {
        return repository.findAll();
    }

    public UtilisateurNonAbonne getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public UtilisateurNonAbonne save(UtilisateurNonAbonne entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<UtilisateurNonAbonne> findByCarteBancaire(String carteBancaire) {
        return repository.findByCarteBancaire(carteBancaire);
    }
    
}