
package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Historique;
import java.util.*;

@Repository
public interface HistoriqueRepository extends JpaRepository<Historique, Long> {
    // Récupère l'historique par utilisateur
    List<Historique> findByUtilisateurId(Long utilisateurId);
}
