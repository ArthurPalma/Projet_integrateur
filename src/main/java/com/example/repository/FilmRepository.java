
package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entities.Film;
import java.util.*;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    // Trouve les films par genre
    List<Film> findByGenre(String genre);

    // Trouve les films contenant un acteur spécifique
    List<Film> findByActeurContaining(String acteur);

    // Récupère les films les mieux notés
    @Query("SELECT f FROM Film f ORDER BY f.rating DESC")
    List<Film> findTopRated();
}
