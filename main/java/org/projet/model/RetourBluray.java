package main.java.org.projet.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;


@Entity(name = "RetourBluray")
public class RetourBluray {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRetourBluray")
    private Long idRetourBluray;

    @OneToOne
    @JoinColumn(name = "fk_idLocation", referencedColumnName = "idLocation", nullable = false)
    @Cascade(CascadeType.PERSIST)
    private Location location;

    @Column(name = "dateRetour")
    private LocalDate dateRetour;

    @Column(name = "etat")
    private Boolean etat;   // true = bon état, false = endommagé

    public RetourBluray(Long idRetourBluray, Location location, LocalDate dateRetour, Boolean etat) {
        this.idRetourBluray = idRetourBluray;
        this.location = location;
        this.dateRetour = dateRetour;
        this.etat = etat;
    }

    public Long getIdRetourBluray() {
        return idRetourBluray;
    }

    public void setIdRetourBluray(Long idRetourBluray) {
        this.idRetourBluray = idRetourBluray;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }
}
