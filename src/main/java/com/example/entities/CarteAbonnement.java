
package com.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cartes_abonnement")
public class CarteAbonnement {

    
    @Id
    private Long id;
    
    @Column
    private Boolean controleParental;
    

    
    @ManyToOne
    private Abonnement abonnement;
    

    // Constructors
    public CarteAbonnement() {}

    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Boolean getControleparental() {
        return controleParental;
    }

    public void setControleparental(Boolean controleParental) {
        this.controleParental = controleParental;
    }
    

    
    public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }
    
}