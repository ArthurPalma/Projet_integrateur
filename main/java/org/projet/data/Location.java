package main.java.org.projet.data;

import java.sql.Date;

public class Location {
    private Long idLocation;
    private Long idUtilisateur;
    private Long idFilm;
    private Date dateLocation;
    private Date dateRetour;
    private boolean retourEffectue;

    // Constructeur avec tous les paramètres
    public Location(Long idLocation, Long idUtilisateur, Long idFilm, Date dateLocation, Date dateRetour, boolean retourEffectue) {
        this.idLocation = idLocation;
        this.idUtilisateur = idUtilisateur;
        this.idFilm = idFilm;
        this.dateLocation = dateLocation;
        this.dateRetour = dateRetour;
        this.retourEffectue = retourEffectue;
    }

    // Getters and Setters
    public Long getIdLocation() { return idLocation; }
    public void setIdLocation(Long idLocation) { this.idLocation = idLocation; }

    public Long getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(Long idUtilisateur) { this.idUtilisateur = idUtilisateur; }

    public Long getIdFilm() { return idFilm; }
    public void setIdFilm(Long idFilm) { this.idFilm = idFilm; }

    public Date getDateLocation() { return dateLocation; }
    public void setDateLocation(Date dateLocation) { this.dateLocation = dateLocation; }

    public Date getDateRetour() { return dateRetour; }
    public void setDateRetour(Date dateRetour) { this.dateRetour = dateRetour; }

    public boolean isRetourEffectue() { return retourEffectue; }
    public void setRetourEffectue(boolean retourEffectue) { this.retourEffectue = retourEffectue; }
}
