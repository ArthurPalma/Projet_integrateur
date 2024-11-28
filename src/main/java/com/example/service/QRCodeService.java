
package com.example.service;

import com.example.entities.QRCode;
import com.example.repository.QRCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QRCodeService {

    private final QRCodeRepository repository;

    public QRCodeService(QRCodeRepository repository) {
        this.repository = repository;
    }

    public List<QRCode> getAll() {
        return repository.findAll();
    }

    public QRCode getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public QRCode save(QRCode entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<QRCode> findByValidite(Boolean validite) {
        return repository.findByValidite(validite);
    }
    
}