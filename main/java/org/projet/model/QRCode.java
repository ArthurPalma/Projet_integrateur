package main.java.org.projet.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "QRCode")
public class QRCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idQRCode")
    private Long idQRCode;

    @Column(name = "validite")
    private boolean validite;

    @ManyToOne
    @JoinColumn(name = "fk_idFilm", referencedColumnName = "idFilm", nullable = false)
    @Cascade(CascadeType.PERSIST)  // Apply cascade for persistence
    private Film film;

    public QRCode(boolean validite, Film film) {
        this.validite = validite;
        this.film = film;
    }

    // Getters and Setters
    public boolean isValidite() {
        return validite;
    }

    public void setValidite(boolean validite) {
        this.validite = validite;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Long getIdQRCode() {
        return idQRCode;
    }

    public void setIdQRCode(Long idQRCode) {
        this.idQRCode = idQRCode;
    }
}
