package main.java.org.projet.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Cascade;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Historique")
public class Historique {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHistorique")
    private Long idHistorique;

    @OneToOne
    @JoinColumn(name = "fk_idLocation", referencedColumnName = "idLocation", nullable = false)
    @Cascade(CascadeType.PERSIST) 
    private Location location;

    @Column(name = "statut")
    private boolean statut;

    @ManyToOne
    @JoinColumn(name = "fk_idUtilsateurNonAbonne", referencedColumnName = "idUtilsateurNonAbonne", nullable = false)
    @Cascade(CascadeType.PERSIST) 
    private UtilisateurNonAbonne idUtilsateurNonAbonne;  

    @Column(name = "tarif")
    private double tarif;

    @Column(name = "dateDebut")
    private LocalDate dateDebut;

    @Column(name = "dateRetour")
    private LocalDate dateRetour;

    @ElementCollection
    @Column(name = "ListeDeFilm")
    @Size(max = 3, message = "Maximum 3 films allowed per historical record.")
    private List<String> ListeDeFilm;  

    public Historique(Long idHistorique, Location location, boolean statut, UtilisateurNonAbonne idUtilsateurNonAbonne, double tarif, LocalDate dateDebut, LocalDate dateRetour, List<String> ListeDeFilm) {
        this.idHistorique = idHistorique;
        this.location = location;
        this.statut = statut;
        this.idUtilsateurNonAbonne = idUtilsateurNonAbonne;
        this.tarif = tarif;
        this.dateDebut = dateDebut;
        this.dateRetour = dateRetour;
        this.ListeDeFilm = ListeDeFilm;
    }

    public Long getIdHistorique() {
        return idHistorique;
    }

    public void setIdHistorique(Long idHistorique) {
        this.idHistorique = idHistorique;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public UtilisateurNonAbonne getUtilisateur() {
        return idUtilsateurNonAbonne;
    }

    public void setUtilisateur(UtilisateurNonAbonne idUtilsateurNonAbonne) {
        this.idUtilsateurNonAbonne = idUtilsateurNonAbonne;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    public List<String> getListeDeFilm() {
        return ListeDeFilm;
    }

    public void setListeDeFilm(List<String> ListeDeFilm) {
        this.ListeDeFilm = ListeDeFilm;
    }

}
