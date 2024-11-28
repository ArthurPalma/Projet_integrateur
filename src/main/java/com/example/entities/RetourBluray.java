
package com.example.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "retours_bluray")
public class RetourBluray {

    
    @Id
    private Long id;
    
    @Column
    private Date dateRetour;
    
    @Column
    private Boolean etat;
    

    
    @OneToOne
    private Location location;
    

    // Constructors
    public RetourBluray() {}

    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getDateretour() {
        return dateRetour;
    }

    public void setDateretour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }
    
    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }
    

    
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
}