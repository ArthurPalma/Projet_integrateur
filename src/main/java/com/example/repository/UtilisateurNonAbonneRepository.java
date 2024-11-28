
package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.UtilisateurNonAbonne;
import java.util.*;

@Repository
public interface UtilisateurNonAbonneRepository extends JpaRepository<UtilisateurNonAbonne, Long> {
    // Trouve les non-abonnés par carte bancaire
    List<UtilisateurNonAbonne> findByCarteBancaire(String carteBancaire);
}
