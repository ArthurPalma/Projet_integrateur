package main.java.org.projet.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity(name = "Abonnement")
public class Abonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAbonnement")
    private Long idAbonnement;

    @ManyToOne
    @JoinColumn(name = "fk_idUtilsateurAbonne", referencedColumnName = "idUtilsateurAbonne", nullable = false)
    @Cascade(CascadeType.PERSIST) 
    private UtilsateurAbonne idUtilsateurAbonne;  // Reference to the UtilsateurAbonne entity

    @OneToOne
    @JoinColumn(name = "fk_idCarteAbonnement", referencedColumnName = "idCarteAbonnement", nullable = false)
    @Cascade(CascadeType.PERSIST) 
    private CarteAbonnement carteAbonnement;  // Reference to the CarteAbonnement entity

    @Column(name = "numLocation")
    private int numLocation;

    public Abonnement(Long idAbonnement, UtilsateurAbonne idUtilsateurAbonne, CarteAbonnement carteAbonnement, int numLocation) {
        this.idAbonnement = idAbonnement;
        this.idUtilsateurAbonne = idUtilsateurAbonne;
        this.carteAbonnement = carteAbonnement;
        this.numLocation = numLocation;
    }

    public Long getIdAbonnement() {
        return idAbonnement;
    }

    public void setIdAbonnement(Long idAbonnement) {
        this.idAbonnement = idAbonnement;
    }

    public UtilsateurAbonne getUtilisateur() {
        return idUtilsateurAbonne;
    }

    public void setUtilisateur(UtilsateurAbonne idUtilsateurAbonne) {
        this.idUtilsateurAbonne = idUtilsateurAbonne;
    }

    public CarteAbonnement getCarteAbonnement() {
        return carteAbonnement;
    }

    public void setCarteAbonnement(CarteAbonnement carteAbonnement) {
        this.carteAbonnement = carteAbonnement;
    }

    public int getNumLocation() {
        return numLocation;
    }

    public void setNumLocation(int numLocation) {
        this.numLocation = numLocation;
    }
}
