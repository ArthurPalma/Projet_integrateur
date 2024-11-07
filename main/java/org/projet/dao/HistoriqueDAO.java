package main.java.org.projet.dao;

import main.java.org.projet.data.Historique;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueDAO implements CRUDRepository<Historique> {
    private Connection connection;

    public HistoriqueDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Historique historique) {
        String sql = "INSERT INTO Historique (idUtilisateur, idFilm, dateLocation, dateRetour) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, historique.getIdUtilisateur());
            stmt.setLong(2, historique.getIdFilm());
            stmt.setDate(3, historique.getDateLocation());
            stmt.setDate(4, historique.getDateRetour());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Historique findById(Long id) {
        String sql = "SELECT * FROM Historique WHERE idHistorique = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Historique(
                    rs.getLong("idHistorique"),
                    rs.getLong("idUtilisateur"),
                    rs.getLong("idFilm"),
                    rs.getDate("dateLocation"),
                    rs.getDate("dateRetour")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Historique> findAll() {
        List<Historique> historiques = new ArrayList<>();
        String sql = "SELECT * FROM Historique";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                historiques.add(new Historique(
                    rs.getLong("idHistorique"),
                    rs.getLong("idUtilisateur"),
                    rs.getLong("idFilm"),
                    rs.getDate("dateLocation"),
                    rs.getDate("dateRetour")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historiques;
    }

    @Override
    public void update(Historique historique) {
        String sql = "UPDATE Historique SET idUtilisateur = ?, idFilm = ?, dateLocation = ?, dateRetour = ? WHERE idHistorique = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, historique.getIdUtilisateur());
            stmt.setLong(2, historique.getIdFilm());
            stmt.setDate(3, historique.getDateLocation());
            stmt.setDate(4, historique.getDateRetour());
            stmt.setLong(5, historique.getIdHistorique());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Historique historique) {
        String sql = "DELETE FROM Historique WHERE idHistorique = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, historique.getIdHistorique());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
