package main.java.org.projet.data;

public class ClientAbonne extends Utilisateur {
    public ClientAbonne(Long idUtilisateur, String nom, String prenom, String email, boolean abonne) {
        super(idUtilisateur, nom, prenom, email, abonne);
        //TODO Auto-generated constructor stub
    }
    private double solde;          // Solde disponible sur la carte d'abonnement
    private int nbLocations;       // Nombre de locations mensuelles

    // Getters and Setters
    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }

    public int getNbLocations() { return nbLocations; }
    public void setNbLocations(int nbLocations) { this.nbLocations = nbLocations; }
}
