
package com.example.service;

import com.example.entities.Film;
import com.example.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    private final FilmRepository repository;

    public FilmService(FilmRepository repository) {
        this.repository = repository;
    }

    public List<Film> getAll() {
        return repository.findAll();
    }

    public Film getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Film save(Film entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Film> findByGenre(String genre) {
        return repository.findByGenre(genre);
    }
    
    public List<Film> findByActeurContaining(String acteur) {
        return repository.findByActeurContaining(acteur);
    }
    
    public List<Film> findTopRated() {
        return repository.findTopRated();
    }
    
}