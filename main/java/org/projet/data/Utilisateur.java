package main.java.org.projet.data;

public class Utilisateur {
    private Long idUtilisateur;
    private String nom;
    private String prenom;
    private String email;
    private boolean abonne;

    // Constructeur avec tous les paramètres
    public Utilisateur(Long idUtilisateur, String nom, String prenom, String email, boolean abonne) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.abonne = abonne;
    }

    // Getters and Setters
    public Long getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(Long idUtilisateur) { this.idUtilisateur = idUtilisateur; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isAbonne() { return abonne; }
    public void setAbonne(boolean abonne) { this.abonne = abonne; }
}
