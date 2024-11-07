package main.java.org.projet.data;

import java.sql.Date;

public class Historique {
    private Long idHistorique;
    private Long idUtilisateur;
    private Long idFilm;
    private Date dateLocation;
    private Date dateRetour;

    // Constructeur avec tous les paramètres
    public Historique(Long idHistorique, Long idUtilisateur, Long idFilm, Date dateLocation, Date dateRetour) {
        this.idHistorique = idHistorique;
        this.idUtilisateur = idUtilisateur;
        this.idFilm = idFilm;
        this.dateLocation = dateLocation;
        this.dateRetour = dateRetour;
    }

    // Getters and Setters
    public Long getIdHistorique() { return idHistorique; }
    public void setIdHistorique(Long idHistorique) { this.idHistorique = idHistorique; }

    public Long getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(Long idUtilisateur) { this.idUtilisateur = idUtilisateur; }

    public Long getIdFilm() { return idFilm; }
    public void setIdFilm(Long idFilm) { this.idFilm = idFilm; }

    public Date getDateLocation() { return dateLocation; }
    public void setDateLocation(Date dateLocation) { this.dateLocation = dateLocation; }

    public Date getDateRetour() { return dateRetour; }
    public void setDateRetour(Date dateRetour) { this.dateRetour = dateRetour; }
}
