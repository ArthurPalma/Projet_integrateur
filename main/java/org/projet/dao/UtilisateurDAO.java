package main.java.org.projet.dao;

import main.java.org.projet.data.Utilisateur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO implements CRUDRepository<Utilisateur> {
    private Connection connection;

    public UtilisateurDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Utilisateur utilisateur) {
        String sql = "INSERT INTO Utilisateur (nom, prenom, email, abonne) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setBoolean(4, utilisateur.isAbonne());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Utilisateur findById(Long id) {
        String sql = "SELECT * FROM Utilisateur WHERE idUtilisateur = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Utilisateur(
                    rs.getLong("idUtilisateur"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getBoolean("abonne")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Utilisateur> findAll() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM Utilisateur";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                utilisateurs.add(new Utilisateur(
                    rs.getLong("idUtilisateur"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getBoolean("abonne")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    @Override
    public void update(Utilisateur utilisateur) {
        String sql = "UPDATE Utilisateur SET nom = ?, prenom = ?, email = ?, abonne = ? WHERE idUtilisateur = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setBoolean(4, utilisateur.isAbonne());
            stmt.setLong(5, utilisateur.getIdUtilisateur());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Utilisateur utilisateur) {
        String sql = "DELETE FROM Utilisateur WHERE idUtilisateur = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, utilisateur.getIdUtilisateur());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
