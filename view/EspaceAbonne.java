package view;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EspaceAbonne extends JFrame {
    private static String currentUsername; 

    
    public static void setCurrentUsername(String username) {
        currentUsername = username;
    }

    public EspaceAbonne() {
        
        String[] userInfo = fetchNomPrenom(currentUsername);

        
        String nom = userInfo[0];
        String prenom = userInfo[1];

        setTitle("Espace Abonné - AL2000");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 248, 255));
        mainPanel.setLayout(new BorderLayout());

        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(25, 25, 112)); 
        JLabel headerLabel = new JLabel("Bienvenue " + nom + " " + prenom + " dans votre espace abonné - AL2000");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 24));
        headerPanel.add(headerLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(240, 248, 255));
        menuPanel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton profileButton = new JButton("Profil");
        profileButton.setFont(new Font("Arial", Font.PLAIN, 18));
        JButton searchMovieButton = new JButton("Rechercher un film");
        searchMovieButton.setFont(new Font("Arial", Font.PLAIN, 18));
        JButton rentMovieButton = new JButton("Louer un film");
        rentMovieButton.setFont(new Font("Arial", Font.PLAIN, 18));
        JButton rentalHistoryButton = new JButton("Historique de location");
        rentalHistoryButton.setFont(new Font("Arial", Font.PLAIN, 18));
        JButton cardBalanceButton = new JButton("Information sur votre carte Abonnement");
        cardBalanceButton.setFont(new Font("Arial", Font.PLAIN, 18));

        menuPanel.add(profileButton);
        menuPanel.add(searchMovieButton);
        menuPanel.add(rentMovieButton);
        menuPanel.add(rentalHistoryButton);
        menuPanel.add(cardBalanceButton);
        mainPanel.add(menuPanel, BorderLayout.WEST);

       
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(25, 25, 112));
        JLabel footerLabel = new JLabel("Location de films en Blu-rays et QrCodes");
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setFont(new Font("Serif", Font.ITALIC, 16));
        footerPanel.add(footerLabel);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(240, 248, 255));
        centerPanel.setLayout(new BorderLayout());



        mainPanel.add(centerPanel, BorderLayout.CENTER);

        
        profileButton.addActionListener(e -> {
            new Profile(currentUsername).setVisible(true);
            dispose();
        });
        searchMovieButton.addActionListener(e -> {
            new FilmAbonne(currentUsername).setVisible(true);
            dispose();
        });
        rentMovieButton.addActionListener(e -> {
            new FilmAbonne(currentUsername).setVisible(true);
            dispose();
        });
        rentalHistoryButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Page d'historique de location en cours de développement.", "Historique de location", JOptionPane.INFORMATION_MESSAGE));
        cardBalanceButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Page de solde de la carte en cours de développement.", "Solde de la carte", JOptionPane.INFORMATION_MESSAGE));

        
        add(mainPanel);
    }

    
    private String[] fetchNomPrenom(String username) {
        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur"; 
        String user = "root"; 
        String dbPassword = ""; 

        String[] userInfo = { "Nom inconnu", "Prénom inconnu" }; 

      
        String sql = "SELECT nom, prenom FROM utilisateurabonne WHERE email = ?";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userInfo[0] = resultSet.getString("nom");   
                userInfo[1] = resultSet.getString("prenom");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des données utilisateur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        return userInfo;
    }
}
