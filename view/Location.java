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

public class Location extends JFrame {
 
    private String filmTitle;
    private String format;
    private long utilisateurID;
    private long filmID;
    private long utilisateurNonAbonneID;

    public Location(String filmTitle, long utilisateurID, long filmID) {
        this.filmTitle = filmTitle;
        this.utilisateurID = utilisateurID;
        this.filmID = filmID;
        setTitle("Louer un Film");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel rentPanel = new JPanel();
        rentPanel.setBackground(new Color(240, 248, 255));
        rentPanel.setLayout(new BorderLayout(10, 10));
        rentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel rentLabel = new JLabel("Louer le film: " + filmTitle, JLabel.CENTER);
        rentLabel.setFont(new Font("Arial", Font.BOLD, 20));
        rentLabel.setForeground(new Color(25, 25, 112));
        rentPanel.add(rentLabel, BorderLayout.NORTH);

        JPanel formatPanel = new JPanel();
        formatPanel.setBackground(new Color(240, 248, 255));
        formatPanel.setLayout(new GridLayout(2, 1, 10, 10));

        JRadioButton bluRayButton = new JRadioButton("BluRay");
        bluRayButton.setFont(new Font("Arial", Font.PLAIN, 16));
        bluRayButton.setBackground(new Color(240, 248, 255));

        JRadioButton qrCodeButton = new JRadioButton("QrCode");
        qrCodeButton.setFont(new Font("Arial", Font.PLAIN, 16));
        qrCodeButton.setBackground(new Color(240, 248, 255));

        ButtonGroup formatGroup = new ButtonGroup();
        formatGroup.add(bluRayButton);
        formatGroup.add(qrCodeButton);

        Bluray bluray = new Bluray();
        bluray.checkBluRayAvailability(filmID, bluRayButton, qrCodeButton);

        formatPanel.add(bluRayButton);
        formatPanel.add(qrCodeButton);

        rentPanel.add(formatPanel, BorderLayout.CENTER);

        JButton confirmButton = new JButton("Confirmer la location");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmButton.setBackground(new Color(25, 25, 112));
        confirmButton.setForeground(Color.WHITE);
        rentPanel.add(confirmButton, BorderLayout.SOUTH);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!bluRayButton.isSelected() && !qrCodeButton.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un format.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    format = bluRayButton.isSelected() ? "BluRay" : "QrCode";
                    showPaymentAndPersonalInfoForms();
                }
            }
        });

        add(rentPanel);
    }

    private void showPaymentAndPersonalInfoForms() {
        JPanel personalInfoPanel = new JPanel();
        personalInfoPanel.setLayout(new GridLayout(2, 2, 10, 10));
        personalInfoPanel.setBorder(BorderFactory.createTitledBorder("Informations personnelles"));
        personalInfoPanel.setBackground(new Color(240, 248, 255));

        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();

        personalInfoPanel.add(new JLabel("Nom:"));
        personalInfoPanel.add(nameField);
        personalInfoPanel.add(new JLabel("Prénom:"));
        personalInfoPanel.add(surnameField);

        int personalInfoResult = JOptionPane.showConfirmDialog(null, personalInfoPanel, "Informations personnelles", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (personalInfoResult == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String surname = surnameField.getText();

            if (name.isEmpty() || surname.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs d'information personnelle.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                showPaymentForm(name, surname);
            }
        }
    }

    private void showPaymentForm(String name, String surname) {
        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new GridLayout(3, 2, 10, 10));
        paymentPanel.setBorder(BorderFactory.createTitledBorder("Informations de paiement"));
        paymentPanel.setBackground(new Color(240, 248, 255));

        JTextField cardNumberField = new JTextField();
        JTextField expirationDateField = new JTextField();
        JTextField cvvField = new JTextField();

        paymentPanel.add(new JLabel("Numéro de carte bancaire:"));
        paymentPanel.add(cardNumberField);
        paymentPanel.add(new JLabel("Date d'expiration (MM/AA):"));
        paymentPanel.add(expirationDateField);
        paymentPanel.add(new JLabel("Code CVV:"));
        paymentPanel.add(cvvField);

        int paymentResult = JOptionPane.showConfirmDialog(null, paymentPanel, "Informations de paiement", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (paymentResult == JOptionPane.OK_OPTION) {
            String cardNumber = cardNumberField.getText();
            String expirationDate = expirationDateField.getText();
            String cvv = cvvField.getText();

            if (cardNumber.isEmpty() || expirationDate.isEmpty() || cvv.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs de paiement.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                saveUserAndProcessTransaction(name, surname, cardNumber, expirationDate, cvv);
            }
        }
    }

    private void saveUserAndProcessTransaction(String name, String surname, String cardNumber, String expirationDate, String cvv) {
        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur"; 
        String user = "root";
        String dbPassword = ""; 

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement userStatement = connection.prepareStatement("INSERT INTO utilisateurNonAbonne (nom, prenom, carteBancaire, dateExpiration, cvv) VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            userStatement.setString(1, name);
            userStatement.setString(2, surname);
            userStatement.setString(3, cardNumber);
            userStatement.setString(4, expirationDate);
            userStatement.setString(5, cvv);

            int userRowsAffected = userStatement.executeUpdate();
            if (userRowsAffected > 0) {
                ResultSet generatedKeys = userStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    utilisateurNonAbonneID = generatedKeys.getLong(1);
                    processTransaction(utilisateurNonAbonneID);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement des informations utilisateur: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void processTransaction(long utilisateurNonAbonneID) {
        double price = format.equals("BluRay") ? 5.0 : 3.0; 
        JOptionPane.showMessageDialog(null, "Résumé de la commande:\nFilm: " + filmTitle + "\nFormat: " + format + "\nPrix: " + price + " €", "Récapitulatif", JOptionPane.INFORMATION_MESSAGE);

        int confirm = JOptionPane.showConfirmDialog(null, "Confirmer le paiement de " + price + " € ?", "Confirmation de paiement", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            saveTransaction(utilisateurNonAbonneID, price);
        }
    }

    private void saveTransaction(long utilisateurNonAbonneID, double price) {
        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur"; 
        String user = "root";
        String dbPassword = "";
        LocalDate today = LocalDate.now();
        LocalDate returnDate = today.plusDays(7); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Location (dateDebut, dateRetourPrevue, utilisateurID, FilmID, tarif, support) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, today.format(formatter));
            preparedStatement.setString(2, returnDate.format(formatter));
            preparedStatement.setLong(3, utilisateurNonAbonneID);
            preparedStatement.setLong(4, filmID);
            preparedStatement.setDouble(5, price);
            preparedStatement.setString(6, format);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                if (format.equals("BluRay")) {
                    Bluray bluray = new Bluray();
                    bluray.updateBluRayStock(filmID);
                }
                JOptionPane.showMessageDialog(null, "Paiement réussi! Un reçu vous a été envoyé.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement de la transaction: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Location("Inception", 1, 1).setVisible(true));
    }
}
