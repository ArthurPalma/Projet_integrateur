
package com.example.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "locations")
public class Location {

    
    @Id
    private Long id;
    
    @Column
    private Date dateDebut;
    
    @Column
    private Date dateRetourPrevue;
    
    @Column
    private Double tarif;
    
    @Column
    private String support;
    

    
    @ManyToOne
    private Film film;
    

    // Constructors
    public Location() {}

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
    
    public Date getDateretourprevue() {
        return dateRetourPrevue;
    }

    public void setDateretourprevue(Date dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }
    
    public Double getTarif() {
        return tarif;
    }

    public void setTarif(Double tarif) {
        this.tarif = tarif;
    }
    
    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }
    

    
    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
    
}