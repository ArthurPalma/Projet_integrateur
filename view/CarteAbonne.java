package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CarteAbonne extends JFrame {
    private String currentUsername;
    private String filmTitle;
    private long filmId;
    private String support;
    private double soldeAbonnement;

    public CarteAbonne(String currentUsername, String filmTitle, long filmId, String support) {
        this.currentUsername = currentUsername;
        this.filmTitle = filmTitle;
        this.filmId = filmId;
        this.support = support;

        afficherSoldeCarteAbonne();

        setTitle("Paiement - Carte Abonné");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel montantLabel = new JLabel("Montant à payer: " + getTarif() + " euros");
        montantLabel.setFont(new Font("Arial", Font.BOLD, 18));
        montantLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(montantLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel soldeLabel = new JLabel("Solde actuel: " + soldeAbonnement + " euros");
        soldeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        soldeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(soldeLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel label = new JLabel("Choisissez le mode de paiement");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton carteAbonnementButton = new JButton("Payer avec Carte Abonnement");
        carteAbonnementButton.setFont(new Font("Arial", Font.PLAIN, 16));
        carteAbonnementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(carteAbonnementButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton carteBleueButton = new JButton("Payer avec Carte Bleue");
        carteBleueButton.setFont(new Font("Arial", Font.PLAIN, 16));
        carteBleueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(carteBleueButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton rechargerButton = new JButton("Recharger Carte Abonnement");
        rechargerButton.setFont(new Font("Arial", Font.PLAIN, 16));
        rechargerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(rechargerButton);

        add(panel);

       
        carteAbonnementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verifierSoldeAbonnement()) {
                    if (effectuerPaiementAvecCarteAbonnement()) {
                        JOptionPane.showMessageDialog(null, "Paiement effectué avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                        insererLocation();
                        afficherSoldeCarteAbonne();
                        soldeLabel.setText("Solde actuel: " + soldeAbonnement + " euros");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur lors du paiement.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Solde insuffisant sur la carte d'abonnement.", "Solde insuffisant", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

       
        carteBleueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (effectuerPaiementAvecCarteBleue()) {
                    JOptionPane.showMessageDialog(null, "Paiement par carte bleue effectué avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    insererLocation();
                    afficherSoldeCarteAbonne();
                    soldeLabel.setText("Solde actuel: " + soldeAbonnement + " euros");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur lors du paiement par carte bleue.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    
        rechargerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String montantString = JOptionPane.showInputDialog(null, "Entrez le montant à recharger:", "Recharger Carte Abonnement", JOptionPane.PLAIN_MESSAGE);
                if (montantString != null && !montantString.isEmpty()) {
                    try {
                        double montant = Double.parseDouble(montantString);
                        if (montant > 0) {
                            effectuerRechargeAvecCarteBleue(montant);
                            afficherSoldeCarteAbonne();
                            soldeLabel.setText("Solde actuel: " + soldeAbonnement + " euros");
                        } else {
                            JOptionPane.showMessageDialog(null, "Veuillez entrer un montant valide.", "Montant invalide", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Veuillez entrer un montant valide.", "Montant invalide", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void afficherSoldeCarteAbonne() {
        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur";
        String user = "root";
        String dbPassword = "";

        String query = "SELECT solde FROM carteabonne WHERE utilisateurabonne_id = (SELECT id FROM utilisateurabonne WHERE email = ?)";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, currentUsername);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                soldeAbonnement = resultSet.getDouble("solde");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la vérification du solde: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean verifierSoldeAbonnement() {
        return soldeAbonnement >= getTarif();
    }

    private boolean effectuerPaiementAvecCarteAbonnement() {
        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur";
        String user = "root";
        String dbPassword = "";

        String updateQuery = "UPDATE carteabonne SET solde = solde - ? WHERE utilisateurabonne_id = (SELECT id FROM utilisateurabonne WHERE email = ?)";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setDouble(1, getTarif());
            preparedStatement.setString(2, currentUsername);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                afficherSoldeCarteAbonne();
                JOptionPane.showMessageDialog(this, "Solde mis à jour avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du paiement: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private boolean effectuerPaiementAvecCarteBleue() {
        JTextField numeroCarteField = new JTextField();
        JTextField dateExpirationField = new JTextField();
        JTextField cvvField = new JTextField();

        Object[] message = {
                "Numéro de Carte:", numeroCarteField,
                "Date d'Expiration (MM/YY):", dateExpirationField,
                "CVV:", cvvField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Informations de la Carte Bleue", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String numeroCarte = numeroCarteField.getText();
            String dateExpiration = dateExpirationField.getText();
            String cvv = cvvField.getText();

            if (numeroCarte.isEmpty() || dateExpiration.isEmpty() || cvv.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir toutes les informations de la carte.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            return true;
        }
        return false;
    }

    private void effectuerRechargeAvecCarteBleue(double montant) {
        if (effectuerPaiementAvecCarteBleue()) {
            String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur";
            String user = "root";
            String dbPassword = "";

            String updateQuery = "UPDATE carteabonne SET solde = solde + ? WHERE utilisateurabonne_id = (SELECT id FROM utilisateurabonne WHERE email = ?)";

            try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
                 PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setDouble(1, montant);
                preparedStatement.setString(2, currentUsername);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    afficherSoldeCarteAbonne();
                    JOptionPane.showMessageDialog(this, "Recharge effectuée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la recharge: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void insererLocation() {
        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur";
        String user = "root";
        String dbPassword = "";

        String insertQuery = "INSERT INTO location (dateDebut, dateRetourPrevue, utilisateurID, FilmID, tarif, support) " +
                "VALUES (?, ?, (SELECT id FROM utilisateurabonne WHERE email = ?), ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            LocalDate dateDebut = LocalDate.now();
            LocalDate dateRetourPrevue = dateDebut.plusDays(7);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


            preparedStatement.setString(1, dateDebut.format(formatter));
            preparedStatement.setString(2, dateRetourPrevue.format(formatter));
            preparedStatement.setString(3, currentUsername);
            preparedStatement.setLong(4, filmId);
            preparedStatement.setDouble(5, getTarif());
            preparedStatement.setString(6, support);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                
                JOptionPane.showMessageDialog(this, "Location ajoutée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'insertion de la location: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double getTarif() {
        return support.equalsIgnoreCase("Blu-ray") ? 5.0 : 3.0;
    }
}

