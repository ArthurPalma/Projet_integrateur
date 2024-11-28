
package com.example.service;

import com.example.entities.Historique;
import com.example.repository.HistoriqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoriqueService {

    private final HistoriqueRepository repository;

    public HistoriqueService(HistoriqueRepository repository) {
        this.repository = repository;
    }

    public List<Historique> getAll() {
        return repository.findAll();
    }

    public Historique getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Historique save(Historique entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Historique> findByUtilisateurId(Long utilisateurId) {
        return repository.findByUtilisateurId(utilisateurId);
    }
    
}