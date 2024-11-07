package main.java.org.projet.dao;

import main.java.org.projet.data.Film;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO implements CRUDRepository<Film> {
    private Connection connection;

    public FilmDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Film film) {
        String sql = "INSERT INTO Film (titre, realisateur, genre, dispoPhysique, dateSortie) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, film.getTitre());
            stmt.setString(2, film.getRealisateur());
            stmt.setString(3, film.getGenre());
            stmt.setBoolean(4, film.isDispoPhysique());
            stmt.setDate(5, film.getDateSortie());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Film findById(Long id) {
        String sql = "SELECT * FROM Film WHERE idFilm = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Film(
                    rs.getLong("idFilm"),
                    rs.getString("titre"),
                    rs.getString("realisateur"),
                    rs.getString("genre"),
                    rs.getBoolean("dispoPhysique"),
                    rs.getDate("dateSortie")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Film> findAll() {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT * FROM Film";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                films.add(new Film(
                    rs.getLong("idFilm"),
                    rs.getString("titre"),
                    rs.getString("realisateur"),
                    rs.getString("genre"),
                    rs.getBoolean("dispoPhysique"),
                    rs.getDate("dateSortie")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }

    @Override
    public void update(Film film) {
        String sql = "UPDATE Film SET titre = ?, realisateur = ?, genre = ?, dispoPhysique = ?, dateSortie = ? WHERE idFilm = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, film.getTitre());
            stmt.setString(2, film.getRealisateur());
            stmt.setString(3, film.getGenre());
            stmt.setBoolean(4, film.isDispoPhysique());
            stmt.setDate(5, film.getDateSortie());
            stmt.setLong(6, film.getIdFilm());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Film film) {
        String sql = "DELETE FROM Film WHERE idFilm = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, film.getIdFilm());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
