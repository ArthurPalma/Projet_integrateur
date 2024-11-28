
package com.example.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "films")
public class Film {

    
    @Id
    private Long id;
    
    @Column
    private String titre;
    
    @Column
    private String realisateur;
    
    @ElementCollection
    private List<String> acteurs;
    
    @Column
    private String genre;
    

    
    @OneToMany(mappedBy = "film")
    private List<Location> locations;
    

    // Constructors
    public Film() {}

    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }
    
    public List<String> getActeurs() {
        return acteurs;
    }

    public void setActeurs(List<String> acteurs) {
        this.acteurs = acteurs;
    }
    
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    

    
    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
    
}