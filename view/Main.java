package view;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends JFrame {
    public Main() {
        setTitle("Accueil - AL2000");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 248, 255));
        mainPanel.setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(25, 25, 112));
        JLabel headerLabel = new JLabel("Bienvenue sur AL2000");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 24));
        headerPanel.add(headerLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Menu
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(240, 248, 255));
        menuPanel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton subscriptionButton = new JButton("Espace Abonnement");
        subscriptionButton.setFont(new Font("Arial", Font.PLAIN, 18));
        JButton searchMovieButton = new JButton("Rechercher un film");
        searchMovieButton.setFont(new Font("Arial", Font.PLAIN, 18));
        JButton rentMovieButton = new JButton("Louer un film");
        rentMovieButton.setFont(new Font("Arial", Font.PLAIN, 18));
        JButton aboutButton = new JButton("À propos");
        aboutButton.setFont(new Font("Arial", Font.PLAIN, 18));

        menuPanel.add(subscriptionButton);
        menuPanel.add(searchMovieButton);
        menuPanel.add(rentMovieButton);
        menuPanel.add(aboutButton);
        mainPanel.add(menuPanel, BorderLayout.WEST);

        // Footer
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

        JLabel movieListLabel = new JLabel("Films du moment", JLabel.CENTER);
        movieListLabel.setFont(new Font("Arial", Font.BOLD, 20));
        centerPanel.add(movieListLabel, BorderLayout.NORTH);

        JPanel moviesGridPanel = new JPanel();
        moviesGridPanel.setBackground(new Color(240, 248, 255));
        moviesGridPanel.setLayout(new GridLayout(0, 3, 10, 10)); // Grid layout for movie cards
        centerPanel.add(new JScrollPane(moviesGridPanel), BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        loadMoviesFromDatabase(moviesGridPanel);

        
        subscriptionButton.addActionListener(e -> {
            new Abonnement().setVisible(true);
            dispose();
        });

        searchMovieButton.addActionListener(e -> {
            new Film().setVisible(true);
            dispose();
        });

        rentMovieButton.addActionListener(e -> {
            new Film().setVisible(true);
            dispose();
        });

        aboutButton.addActionListener(e -> {
            getContentPane().removeAll();
            addAboutPage();
            revalidate();
            repaint();
        });

        add(mainPanel);
    }

    private void loadMoviesFromDatabase(JPanel moviesGridPanel) {
        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur"; 
        String user = "root"; 
        String password = ""; 

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT titre, couverture FROM film")) {

            moviesGridPanel.removeAll();
            while (resultSet.next()) {
                String titre = resultSet.getString("titre");
                String couverture = resultSet.getString("couverture");

                
                JPanel movieCard = new JPanel();
                movieCard.setLayout(new BorderLayout());
                movieCard.setBackground(Color.WHITE);
                movieCard.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

                
                JLabel imageLabel;
                if (couverture != null && !couverture.isEmpty()) {
                    ImageIcon icon = new ImageIcon(couverture);
                    Image image = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    imageLabel = new JLabel(new ImageIcon(image));
                } else {
                    imageLabel = new JLabel(new ImageIcon("placeholder.png")); 
                }
                imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                movieCard.add(imageLabel, BorderLayout.CENTER);

                
                JLabel titleLabel = new JLabel(titre, JLabel.CENTER);
                titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
                movieCard.add(titleLabel, BorderLayout.SOUTH);

                moviesGridPanel.add(movieCard);
            }
            moviesGridPanel.revalidate();
            moviesGridPanel.repaint();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des films: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addAboutPage() {
        JPanel aboutPanel = new JPanel();
        aboutPanel.setBackground(new Color(240, 248, 255));
        aboutPanel.setLayout(new BorderLayout());

        JLabel aboutLabel = new JLabel("À propos", JLabel.CENTER);
        aboutLabel.setFont(new Font("Arial", Font.BOLD, 24));
        aboutPanel.add(aboutLabel, BorderLayout.NORTH);

        JLabel aboutInfo = new JLabel("AL2000 - Location de films et Blu-rays\nDéveloppé par l'équipe Master Info 1.", JLabel.CENTER);
        aboutInfo.setFont(new Font("Serif", Font.ITALIC, 16));
        aboutPanel.add(aboutInfo, BorderLayout.CENTER);

        add(aboutPanel);
       
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton returnButton = new JButton("Retour à l'accueil");
        returnButton.setFont(new Font("Arial", Font.BOLD, 16));
        returnButton.setBackground(new Color(25, 25, 112));
        returnButton.setForeground(Color.WHITE);
        returnButton.addActionListener(e -> {
            new Main().setVisible(true);
            dispose();
        });
        buttonPanel.add(returnButton);

        add(buttonPanel, BorderLayout.SOUTH);
    
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}

