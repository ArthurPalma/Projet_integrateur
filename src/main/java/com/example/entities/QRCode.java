
package com.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "qrcodes")
public class QRCode {

    
    @Id
    private Long id;
    
    @Column
    private Boolean validite;
    

    

    // Constructors
    public QRCode() {}

    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Boolean getValidite() {
        return validite;
    }

    public void setValidite(Boolean validite) {
        this.validite = validite;
    }
    

    
}