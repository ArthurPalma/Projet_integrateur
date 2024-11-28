package main.java.org.projet.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "CarteAbonnement")
public class CarteAbonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCarteAbonnement")
    private Long idCarteAbonnement;

    @Column(name = "solde")
    private double solde;

    @Column(name = "controleParental")
    private boolean controleParental;

    @ElementCollection
    @CollectionTable(name = "list_dinterdictions", joinColumns = @JoinColumn(name = "idCarteAbonnement"))
    @Column(name = "genre")  // Column name for the genres
    private List<String> listDinterdictions;

    public CarteAbonnement(Long idCarteAbonnement, double solde, boolean controleParental,
            List<String> listDinterdictions) {
        this.idCarteAbonnement = idCarteAbonnement;
        this.solde = solde;
        this.controleParental = controleParental;
        this.listDinterdictions = listDinterdictions;
    }

    public Long getIdCarteAbonnement() {
        return idCarteAbonnement;
    }

    public void setIdCarteAbonnement(Long idCarteAbonnement) {
        this.idCarteAbonnement = idCarteAbonnement;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public boolean isControleParental() {
        return controleParental;
    }

    public void setControleParental(boolean controleParental) {
        this.controleParental = controleParental;
    }

    public List<String> getListDinterdictions() {
        return listDinterdictions;
    }

}
