
package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.UtilisateurAbonne;

import jakarta.transaction.Transactional;

import java.util.*;

@Repository
public interface UtilisateurAbonneRepository extends JpaRepository<UtilisateurAbonne, Long> {
    // Trouve les abonnés par nom
    List<UtilisateurAbonne> findByNom(String nom);

    // Met à jour l'adresse d'un abonné
    @Modifying
    @Transactional
    @Query("UPDATE UtilisateurAbonne u SET u.adresse = :newAdresse WHERE u.id = :id")
    void updateAdresse(@Param("id") Long id, @Param("newAdresse") String newAdresse);
}
