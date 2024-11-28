package main.java.org.projet.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ElementCollection;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLocation")
    private Long idLocation;

    @Column(name = "dateDebut")
    private LocalDate dateDebut;

    @Column(name = "dateRetourPrevue")
    private LocalDate dateRetourPrevue;

    @ElementCollection
    @Column(name = "ListeDeFilm")
    @Size(max = 3, message = "Maximum 3 films allowed per location.")
    private List<String> ListeDeFilm;

    @Column(name = "tarif")
    private double tarif;

    @Column(name = "support")
    private String support;

    @ManyToOne
    @JoinColumn(name = "fk_idClient", referencedColumnName = "idUtilsateurNonAbonne", nullable = false)
    @Cascade(CascadeType.PERSIST) 
    private UtilisateurNonAbonne idUtilsateurNonAbonne;  // Changed to link to UtilisateurNonAbonne

    public Location() {
    }

    public Location(Long idLocation, LocalDate dateDebut, LocalDate dateRetourPrevue, List<String> ListeDeFilm, double tarif,
                    String support, UtilisateurNonAbonne idUtilsateurNonAbonne) {
        this.idLocation = idLocation;
        this.dateDebut = dateDebut;
        this.dateRetourPrevue = dateRetourPrevue;
        this.ListeDeFilm = ListeDeFilm;
        this.tarif = tarif;
        this.support = support;
        this.idUtilsateurNonAbonne = idUtilsateurNonAbonne;
    }

    // Getters and Setters
    public Long getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Long idLocation) {
        this.idLocation = idLocation;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public List<String> getListeDeFilm() {
        return ListeDeFilm;
    }

    public void setListeDeFilm(List<String> ListeDeFilm) {
        this.ListeDeFilm = ListeDeFilm;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public UtilisateurNonAbonne getUtilisateur() {
        return idUtilsateurNonAbonne;
    }

    public void setUtilisateur(UtilisateurNonAbonne idUtilsateurNonAbonne) {
        this.idUtilsateurNonAbonne = idUtilsateurNonAbonne;
    }
}
