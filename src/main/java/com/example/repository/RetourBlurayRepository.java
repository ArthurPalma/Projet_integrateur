
package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entities.RetourBluray;
import java.util.*;

@Repository
public interface RetourBlurayRepository extends JpaRepository<RetourBluray, Long> {
    // Vérifie les retards
    @Query("SELECT r FROM RetourBluray r WHERE r.dateRetour < CURRENT_DATE")
    List<RetourBluray> findLateReturns();
}
