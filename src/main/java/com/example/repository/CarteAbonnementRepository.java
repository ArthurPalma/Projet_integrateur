
package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.CarteAbonnement;

import jakarta.transaction.Transactional;

import java.util.*;

@Repository
public interface CarteAbonnementRepository extends JpaRepository<CarteAbonnement, Long> {
    // Trouve les cartes avec un contrôle parental actif
    List<CarteAbonnement> findByControlParental(Boolean controlParental);

    // Met à jour le solde d'une carte spécifique
    @Modifying
    @Transactional
    @Query("UPDATE CarteAbonnement c SET c.solde = :newSolde WHERE c.id = :id")
    void updateSolde(@Param("id") Long id, @Param("newSolde") Double newSolde);
}
