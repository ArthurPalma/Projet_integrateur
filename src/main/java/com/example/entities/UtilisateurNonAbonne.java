
package com.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "utilisateurs_non_abonnes")
public class UtilisateurNonAbonne {

    
    @Id
    private Long id;
    
    @Column
    private String nom;
    
    @Column
    private String adresse;
    
    @Column
    private String carteBancaire;
    

    // Constructors
    public UtilisateurNonAbonne() {}

    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String getCartebancaire() {
        return carteBancaire;
    }

    public void setCartebancaire(String carteBancaire) {
        this.carteBancaire = carteBancaire;
    }
    

    
}