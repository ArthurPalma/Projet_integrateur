
package com.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "abonnements")
public class Abonnement {

    
    @Id
    private Long id;
    
    @Column
    private Integer nbLocMax;
    
    @Column
    private Double solde;
    

    
    @ManyToOne
    private UtilisateurAbonne utilisateur;
    

    // Constructors
    public Abonnement() {}

    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getNblocmax() {
        return nbLocMax;
    }

    public void setNblocmax(Integer nbLocMax) {
        this.nbLocMax = nbLocMax;
    }
    
    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }
    

    
    public UtilisateurAbonne getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurAbonne utilisateur) {
        this.utilisateur = utilisateur;
    }
    
}