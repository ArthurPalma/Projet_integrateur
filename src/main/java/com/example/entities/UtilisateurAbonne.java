
package com.example.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "utilisateurs_abonnes")
public class UtilisateurAbonne {

    
    @Id
    private Long id;
    
    @Column
    private String nom;
    
    @Column
    private String adresse;
    
    @Column
    private String email;
    
    @Column
    private String motDePasse;
    

    
    @OneToMany(mappedBy = "utilisateurAbonne")
    private List<Historique> historiques;
    

    // Constructors
    public UtilisateurAbonne() {}

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
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMotdepasse() {
        return motDePasse;
    }

    public void setMotdepasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    

    
    public List<Historique> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(List<Historique> historiques) {
        this.historiques = historiques;
    }
    
}