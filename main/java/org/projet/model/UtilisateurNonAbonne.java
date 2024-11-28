package main.java.org.projet.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity(name = "UtilisateurNonAbonne")
public class UtilisateurNonAbonne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUtilisateur")
    private Long idUtilsateurNonAbonne;

    @Column(name = "carteBleu", nullable = false)
    private String carteBleu;

    public UtilisateurNonAbonne(Long idUtilsateurNonAbonne, String carteBleu) {
        this.idUtilsateurNonAbonne = idUtilsateurNonAbonne;
        this.carteBleu = carteBleu;
    }

    public Long getIdUtilisateur() {
        return idUtilsateurNonAbonne;
    }

    public void setIdUtilisateur(Long idUtilsateurNonAbonne) {
        this.idUtilsateurNonAbonne = idUtilsateurNonAbonne;
    }

    public String getCarteBleu() {
        return carteBleu;
    }

    public void setCarteBleu(String carteBleu) {
        this.carteBleu = carteBleu;
    }
}
