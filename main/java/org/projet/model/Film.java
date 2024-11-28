package main.java.org.projet.model;

import java.util.List;
import java.time.LocalDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ElementCollection;


@Entity(name = "Film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFilm")
    private Long idFilm;

    @Column(name = "titre")
    private String titre;

    @Column(name = "realisateur")
    private String realisateur;

    @Column(name = "genre")
    private String genre;

    @Column(name = "dispoPhysique")
    private boolean dispoPhysique;

    @Column(name = "dateSortie")
    private LocalDate dateSortie;

    @ElementCollection
    @Column(name = "acteurs")
    private List<String> acteurs;

    public Film(Long idFilm,
            String titre,
            String realisateur,
            String genre,
            boolean dispoPhysique,
            LocalDate dateSortie,
            List<String> acteurs) {
        this.idFilm = idFilm;
        this.titre = titre;
        this.realisateur = realisateur;
        this.genre = genre;
        this.dispoPhysique = dispoPhysique;
        this.dateSortie = dateSortie;
        this.acteurs = acteurs;
    }

    public Long getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(Long idFilm) {
        this.idFilm = idFilm;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isDispoPhysique() {
        return dispoPhysique;
    }

    public void setDispoPhysique(boolean dispoPhysique) {
        this.dispoPhysique = dispoPhysique;
    }

    public LocalDate getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    public List<String> getActeurs() {
        return acteurs;
    }

    public void setActeurs(List<String> acteurs) {
        this.acteurs = acteurs;
    }


}
