
package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.Abonnement;
import java.util.*;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
    // Récupère un abonnement à partir du numéro de carte
    Optional<Abonnement> findByNumCarte(Long numCarte);

    // Vérifie si les limites d'abonnement sont respectées pour un montant donné
    @Query("SELECT a FROM Abonnement a WHERE a.numCarte = :numCarte AND a.solde >= :montant")
    Optional<Abonnement> verifyLimits(@Param("numCarte") Long numCarte, @Param("montant") Double montant);
}
