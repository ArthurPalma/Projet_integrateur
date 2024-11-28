package main.java.org.projet.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "Bluray")
public class Bluray {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBluray")
    private Long idBluray;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_idFilm", referencedColumnName = "idFilm", nullable = false)
    private Film film;

    public Bluray(Long idBluray, Film film) {
        this.idBluray = idBluray;
        this.film = film;
    }

    public Long getIdBluray() {
        return idBluray;
    }

    public void setIdBluray(Long idBluray) {
        this.idBluray = idBluray;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}