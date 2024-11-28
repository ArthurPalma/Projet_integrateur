package main.java.org.projet.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "UtilsateurAbonne")
public class UtilsateurAbonne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUtilisateur")
    private Long idUtilsateurAbonne;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "tel")
    private String tel;

    @Column(name = "carteBleu", nullable = false)
    private String carteBleu;

    @Column(name = "dateNaissance")
    private LocalDate dateNaissance;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    public UtilsateurAbonne(Long idUtilsateurAbonne, String nom, String prenom, String adresse, String tel, String carteBleu,
            LocalDate dateNaissance, String password, String username) {
        this.idUtilsateurAbonne = idUtilsateurAbonne;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.carteBleu = carteBleu;
        this.dateNaissance = dateNaissance;
        this.password = password;
        this.username = username;
    }

    // public UtilisateurNonAbonne(Long idUtilisateur, String carteBleu){
    // this.idUtilisateur = idUtilisateur;
    // this.carteBleu = carteBleu;
    // }

    public Long getIdUtilisateur() {
        return idUtilsateurAbonne;
    }

    public void setIdUtilisateur(Long idUtilsateurAbonne) {
        this.idUtilsateurAbonne = idUtilsateurAbonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCarteBleu() {
        return carteBleu;
    }

    public void setCarteBleu(String carteBleu) {
        this.carteBleu = carteBleu;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
