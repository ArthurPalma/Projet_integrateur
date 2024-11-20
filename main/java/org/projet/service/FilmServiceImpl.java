package main.java.org.projet.service;

import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import main.java.org.projet.dao.FilmDAO;
import main.java.org.projet.data.Film;
import java.util.Date;

public class FilmServiceImpl {
    private FilmDAO FilmDAO;

    public FilmServiceImpl(EntityManagerFactory emf) {
        this.FilmDAO = new FilmDAOImpl(emf);
    }

    @Override
    public addFilm(Film film) {
        this.FilmDAO.addFilm(film);
    }

    @Override
    public deleteFilm(Long idFilm) {
        this.FilmDAO.deleteFilm(idFilm);
    }

    @Override
    public updateFilm(Film film) {
        this.FilmDAO.updateFilm(film);
    }

    @Override
    public List<Film> getAllFilms() {
        return this.FilmDAO.getAllFilms();
    }

    @Override
    public Film getFilmById(Long idFilm) {
        return this.FilmDAO.getFilmById(idFilm);
    }

    @Override
    public List<Film> getFilmByTitre(String titre) {
        return this.FilmDAO.getFilmByTitre(titre);
    }

    @Override
    public List<Film> getFilmByRealisateur(String realisateur) {
        return this.FilmDAO.getFilmByRealisateur(realisateur);
    }

    @Override
    public List<Film> getFilmByGenre(String genre) {
        return this.FilmDAO.getFilmByGenre(genre);
    }

    @Override
    public List<Film> getFilmByDispoPhysique(boolean dispoPhysique) {
        return this.FilmDAO.getFilmByDispoPhysique(dispoPhysique);
    }

    @Override
    public List<Film> getFilmByDateSortie(Date dateSortie) {
        return this.FilmDAO.getFilmByDateSortie(dateSortie);
    }

    @Override
    public FilmDAO getFilmDAO() {
        return this.FilmDAO;
    }



}
