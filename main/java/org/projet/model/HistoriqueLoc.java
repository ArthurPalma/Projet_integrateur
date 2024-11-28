package main.java.org.projet.model;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Cascade;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "HistoriqueLoc")
public class HistoriqueLoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHistoriqueLoc")
    private Long idHistoriqueLoc;

    @ManyToOne
    @JoinColumn(name = "fk_idUtilsateurAbonne", referencedColumnName = "idUtilsateurAbonne", nullable = false)
    @Cascade(CascadeType.PERSIST) 
    private UtilsateurAbonne utilsateurAbonne;  

    @ManyToOne
    @JoinColumn(name = "fk_idCarteAbonnement", referencedColumnName = "idCarteAbonnement", nullable = false)
    @Cascade(CascadeType.PERSIST) 
    private CarteAbonnement carteAbonnement;  

    @Column(name = "tarif")
    private double tarif;

    @Column(name = "dateDebut")
    private LocalDate dateDebut;

    @Column(name = "dateRetour")
    private LocalDate dateRetour;

    @ElementCollection
    @Column(name = "ListeDeFilm")
    private List<String> ListeDeFilm;

    public HistoriqueLoc(Long idHistoriqueLoc, double tarif, LocalDate dateDebut, LocalDate dateRetour,
            UtilsateurAbonne utilsateurAbonne, CarteAbonnement carteAbonnement, List<String> ListeDeFilm) {
        this.idHistoriqueLoc = idHistoriqueLoc;
        this.tarif = tarif;
        this.dateDebut = dateDebut;
        this.dateRetour = dateRetour;
        this.utilsateurAbonne = utilsateurAbonne;
        this.carteAbonnement = carteAbonnement;
        this.ListeDeFilm = ListeDeFilm;
    }

    public Long getIdHistoriqueLoc() {
        return idHistoriqueLoc;
    }

    public void setIdHistoriqueLoc(Long idHistoriqueLoc) {
        this.idHistoriqueLoc = idHistoriqueLoc;
    }

    public UtilsateurAbonne getUtilsateurAbonne() {
        return utilsateurAbonne;
    }

    public void setUtilsateurAbonne(UtilsateurAbonne utilsateurAbonne) {
        this.utilsateurAbonne = utilsateurAbonne;
    }

    public CarteAbonnement getCarteAbonnement() {
        return carteAbonnement;
    }

    public void setCarteAbonnement(CarteAbonnement carteAbonnement) {
        this.carteAbonnement = carteAbonnement;
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
