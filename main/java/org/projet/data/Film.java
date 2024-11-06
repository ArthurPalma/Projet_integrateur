package main.java.org.projet.data;

import java.sql.Date;

public class Film {
    
    private Long idFilm;
    public Long getIdFilm() {
        return idFilm;
    }
    public void setIdFilm(Long idFilm) {
        this.idFilm = idFilm;
    }

    private String titre;
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    private String realisateur;
    public String getRealisateur() {
        return realisateur;
    }
    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    private String genre;
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    private String dispoPhysique;
    public String getDispoPhysique() {
        return dispoPhysique;
    }
    public void setDispoPhysique(String dispoPhysique) {
        this.dispoPhysique = dispoPhysique;
    }

    private String dispoNumerique;
    public String getDispoNumerique() {
        return dispoNumerique;
    }
    public void setDispoNumerique(String dispoNumerique) {
        this.dispoNumerique = dispoNumerique;
    }
    
    private Date dateAjout;
    public Date getDateAjout() {
        return dateAjout;
    }
    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    } 


}


