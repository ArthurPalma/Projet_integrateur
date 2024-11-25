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

class Profile extends JFrame {
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
 
    public Profile(String username) {
        setTitle("Profil Utilisateur");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new GridLayout(8, 2, 10, 10));
        profilePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Nom:");
        nameField = new JTextField();
        nameField.setEditable(false);
        JLabel surnameLabel = new JLabel("Prénom:");
        surnameField = new JTextField();
        surnameField.setEditable(false);
        JLabel addressLabel = new JLabel("Adresse:");
        addressField = new JTextField();
        addressField.setEditable(false);
        JLabel phoneLabel = new JLabel("Numéro de téléphone:");
        phoneField = new JTextField();
        phoneField.setEditable(false);
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        emailField.setEditable(false);
      
        profilePanel.add(nameLabel);
        profilePanel.add(nameField);
        profilePanel.add(surnameLabel);
        profilePanel.add(surnameField);
        profilePanel.add(addressLabel);
        profilePanel.add(addressField);
        profilePanel.add(phoneLabel);
        profilePanel.add(phoneField);
        profilePanel.add(emailLabel);
        profilePanel.add(emailField);
       

        JButton modifyButton = new JButton("Modifier les informations");
        modifyButton.setFont(new Font("Arial", Font.BOLD, 16));
        modifyButton.setBackground(new Color(25, 25, 112));
        modifyButton.setForeground(Color.WHITE);

        JButton backButton = new JButton("Retour à Espace Abonné");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(25, 25, 112));
        backButton.setForeground(Color.WHITE);

        JButton mainButton = new JButton("Retour à l'accueil");
        mainButton.setFont(new Font("Arial", Font.BOLD, 16));
        mainButton.setBackground(new Color(25, 25, 112));
        mainButton.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(modifyButton);
        buttonPanel.add(backButton);
        buttonPanel.add(mainButton);

        add(profilePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showModifyForm(username);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EspaceAbonne().setVisible(true);
                dispose();
            }
        });

        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main().setVisible(true);
                dispose();
            }
        });

        loadUserProfile(username);
    }

    private void loadUserProfile(String username) {
        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur"; // URL de la base de données
        String user = "root"; // Nom d'utilisateur de la base de données
        String dbPassword = ""; // Mot de passe de la base de données

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM utilisateurabonne WHERE email = ?")) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                nameField.setText(resultSet.getString("nom"));
                surnameField.setText(resultSet.getString("prenom"));
                addressField.setText(resultSet.getString("adresse"));
                phoneField.setText(resultSet.getString("telephone"));
                emailField.setText(resultSet.getString("email"));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des informations utilisateur: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showModifyForm(String username) {
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.add(new JLabel("Nom:"));
        JTextField newNameField = new JTextField(nameField.getText());
        formPanel.add(newNameField);
        formPanel.add(new JLabel("Prénom:"));
        JTextField newSurnameField = new JTextField(surnameField.getText());
        formPanel.add(newSurnameField);
        formPanel.add(new JLabel("Adresse:"));
        JTextField newAddressField = new JTextField(addressField.getText());
        formPanel.add(newAddressField);
        formPanel.add(new JLabel("Numéro de téléphone:"));
        JTextField newPhoneField = new JTextField(phoneField.getText());
        formPanel.add(newPhoneField);

        int result = JOptionPane.showConfirmDialog(null, formPanel, "Modifier les informations", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String name = newNameField.getText();
            String surname = newSurnameField.getText();
            String address = newAddressField.getText();
            String phone = newPhoneField.getText();

            if (name.isEmpty() || surname.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                updateUserProfile(username, name, surname, address, phone);
            }
        }
    }

    private void updateUserProfile(String username, String name, String surname, String address, String phone) {
        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur";
        String user = "root";
        String dbPassword = ""; 

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE utilisateurabonne SET nom = ?, prenom = ?, adresse = ?, telephone = ? WHERE email = ?")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, username);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Informations modifiées avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                loadUserProfile(username);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour des informations utilisateur: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
