
package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.QRCode;
import java.util.*;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
    // Trouve un QR code par validité
    List<QRCode> findByValidite(Boolean validite);
}
