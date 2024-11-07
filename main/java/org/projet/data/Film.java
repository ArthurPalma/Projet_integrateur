package main.java.org.projet.data;

import java.sql.Date;

public class Film {
    private Long idFilm;
    private String titre;
    private String realisateur;
    private String genre;
    private boolean dispoPhysique;
    private Date dateSortie;

    // Constructeur avec tous les paramètres
    public Film(Long idFilm, String titre, String realisateur, String genre, boolean dispoPhysique, Date dateSortie) {
        this.idFilm = idFilm;
        this.titre = titre;
        this.realisateur = realisateur;
        this.genre = genre;
        this.dispoPhysique = dispoPhysique;
        this.dateSortie = dateSortie;
    }

    // Getters and Setters
    public Long getIdFilm() { return idFilm; }
    public void setIdFilm(Long idFilm) { this.idFilm = idFilm; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getRealisateur() { return realisateur; }
    public void setRealisateur(String realisateur) { this.realisateur = realisateur; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public boolean isDispoPhysique() { return dispoPhysique; }
    public void setDispoPhysique(boolean dispoPhysique) { this.dispoPhysique = dispoPhysique; }

    public Date getDateSortie() { return dateSortie; }
    public void setDateSortie(Date dateSortie) { this.dateSortie = dateSortie; }
}
