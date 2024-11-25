package view;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bluray {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/projet_integrateur"; 
    private static final String USER = "root"; 
    private static final String DB_PASSWORD = "";

    
    public void checkBluRayAvailability(long filmID, JRadioButton bluRayButton, JRadioButton qrCodeButton) {
        try (Connection connection = DriverManager.getConnection(URL, USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT stockDisponible FROM stockbluray WHERE idFilm = ?")) {
            preparedStatement.setLong(1, filmID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int stockDisponible = resultSet.getInt("stockDisponible");
                if (stockDisponible > 0) {
                    bluRayButton.setEnabled(true);
                    qrCodeButton.setEnabled(true);
                } else {
                    bluRayButton.setEnabled(false);
                    qrCodeButton.setSelected(true);
                    qrCodeButton.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Le format BluRay n'est pas disponible pour ce film. Seul le QRCode est disponible.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                bluRayButton.setEnabled(false);
                qrCodeButton.setSelected(true);
                qrCodeButton.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Le film sélectionné n'est pas présent dans le stock BluRay.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la vérification du stock: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

   
    public void updateBluRayStock(long filmID) {
        try (Connection connection = DriverManager.getConnection(URL, USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE stockbluray SET stockDisponible = stockDisponible - 1 WHERE idFilm = ? AND stockDisponible > 0")) {
            preparedStatement.setLong(1, filmID);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Stock BluRay mis à jour avec succès.");
            } else {
                JOptionPane.showMessageDialog(null, "Impossible de mettre à jour le stock du BluRay.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour du stock BluRay: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
