package main.java.org.projet.service;
import main.java.org.projet.data.Film;
import java.util.List;
import java.sql.Date;

public interface FilmService {
    void addFilm(Film film);
    void deleteFilm(Long idFilm);
    void updateFilm(Film film);
    List<Film> getAllFilms();
    Film getFilmById(Long idFilm);
    List<Film> getFilmByTitre(String titre);
    List<Film> getFilmByRealisateur(String realisateur);
    List<Film> getFilmByGenre(String genre);
    List<Film> getFilmByDispoPhysique(boolean dispoPhysique);
    List<Film> getFilmByDateSortie(Date dateSortie);
}
