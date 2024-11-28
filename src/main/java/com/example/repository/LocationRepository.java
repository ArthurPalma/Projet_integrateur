
package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    // Calcule le tarif d'une location donnée
    @Query("SELECT l.tarif FROM Location l WHERE l.idLoc = :locationId")
    Double calculateTariff(@Param("locationId") Long locationId);
}
