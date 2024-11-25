package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Inscription extends JFrame {
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField addressField;
    private JFormattedTextField birthdateField;
    private JTextField emailField;
    private JTextField passwordField;
    private JTextField phoneField;

    public Inscription() {
        setTitle("Inscription Utilisateur Abonne");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(8, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Nom:");
        nameField = new JTextField();
        JLabel surnameLabel = new JLabel("Prénom:");
        surnameField = new JTextField();
        JLabel addressLabel = new JLabel("Adresse:");
        addressField = new JTextField();
        JLabel birthdateLabel = new JLabel("Date de Naissance (YYYY-MM-DD):");
        birthdateField = new JFormattedTextField(new java.text.SimpleDateFormat("yyyy-MM-dd"));
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordField = new JPasswordField();
        JLabel phoneLabel = new JLabel("Numéro de téléphone:");
        phoneField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(surnameLabel);
        formPanel.add(surnameField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        formPanel.add(birthdateLabel);
        formPanel.add(birthdateField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);

        JButton registerButton = new JButton("Créer un compte");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBackground(new Color(25, 25, 112));
        registerButton.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(registerButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUserAccount();
            }
        });
    }

    private void createUserAccount() {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String address = addressField.getText();
        String birthdate = birthdateField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String phone = phoneField.getText();

        if (name.isEmpty() || surname.isEmpty() || address.isEmpty() || birthdate.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur"; 
        String user = "root"; 
        String dbPassword = ""; 

        String insertQuery = "INSERT INTO utilisateurabonne (nom, prenom, adresse, dateNaissance, email, password, telephone) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, birthdate);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, password);
            preparedStatement.setString(7, phone);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Compte créé avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new Login().setVisible(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la création du compte utilisateur: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Inscription().setVisible(true));
    }
}
