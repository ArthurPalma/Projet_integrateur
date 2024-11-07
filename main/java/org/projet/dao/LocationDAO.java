package main.java.org.projet.dao;

import main.java.org.projet.data.Location;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO implements CRUDRepository<Location> {
    private Connection connection;

    public LocationDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Location location) {
        String sql = "INSERT INTO Location (idUtilisateur, idFilm, dateLocation, dateRetour, retourEffectue) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, location.getIdUtilisateur());
            stmt.setLong(2, location.getIdFilm());
            stmt.setDate(3, location.getDateLocation());
            stmt.setDate(4, location.getDateRetour());
            stmt.setBoolean(5, location.isRetourEffectue());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Location findById(Long id) {
        String sql = "SELECT * FROM Location WHERE idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Location(
                    rs.getLong("idLocation"),
                    rs.getLong("idUtilisateur"),
                    rs.getLong("idFilm"),
                    rs.getDate("dateLocation"),
                    rs.getDate("dateRetour"),
                    rs.getBoolean("retourEffectue")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Location> findAll() {
        List<Location> locations = new ArrayList<>();
        String sql = "SELECT * FROM Location";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                locations.add(new Location(
                    rs.getLong("idLocation"),
                    rs.getLong("idUtilisateur"),
                    rs.getLong("idFilm"),
                    rs.getDate("dateLocation"),
                    rs.getDate("dateRetour"),
                    rs.getBoolean("retourEffectue")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }

    @Override
    public void update(Location location) {
        String sql = "UPDATE Location SET idUtilisateur = ?, idFilm = ?, dateLocation = ?, dateRetour = ?, retourEffectue = ? WHERE idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, location.getIdUtilisateur());
            stmt.setLong(2, location.getIdFilm());
            stmt.setDate(3, location.getDateLocation());
            stmt.setDate(4, location.getDateRetour());
            stmt.setBoolean(5, location.isRetourEffectue());
            stmt.setLong(6, location.getIdLocation());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Location location) {
        String sql = "DELETE FROM Location WHERE idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, location.getIdLocation());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
