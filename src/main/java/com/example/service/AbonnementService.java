
package com.example.service;

import com.example.entities.Abonnement;
import com.example.repository.AbonnementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbonnementService {

    private final AbonnementRepository repository;

    public AbonnementService(AbonnementRepository repository) {
        this.repository = repository;
    }

    public List<Abonnement> getAll() {
        return repository.findAll();
    }

    public Abonnement getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Abonnement save(Abonnement entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    public Optional<Abonnement> findByNumCarte(Long numCarte) {
    return repository.findByNumCarte(numCarte);
    }

    public Optional<Abonnement> verifyLimits(Long numCarte, Double montant) {
    return repository.verifyLimits(numCarte, montant);
    }

}