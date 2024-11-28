
package com.example.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "historiques")
public class Historique {

    
    @Id
    private Long id;
    
    @Column
    private Date dateDebut;
    
    @Column
    private Date dateFin;
    
    @Column
    private Boolean statut;
    

    
    @ManyToOne
    private UtilisateurAbonne utilisateurAbonne;
    

    // Constructors
    public Historique() {}

    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getDatedebut() {
        return dateDebut;
    }

    public void setDatedebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    
    public Date getDatefin() {
        return dateFin;
    }

    public void setDatefin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }
    

    
    public UtilisateurAbonne getUtilisateurabonne() {
        return utilisateurAbonne;
    }

    public void setUtilisateurabonne(UtilisateurAbonne utilisateurAbonne) {
        this.utilisateurAbonne = utilisateurAbonne;
    }
    
}