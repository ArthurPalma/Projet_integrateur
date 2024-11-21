package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainApp extends JFrame {
    private DefaultListModel<String> movieListModel;

    public MainApp() {
       
        setTitle("Accueil - AL2000");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 248, 255)); 
        mainPanel.setLayout(new BorderLayout());

        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(25, 25, 112)); 
        JLabel headerLabel = new JLabel("Bienvenue sur AL2000");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 24));
        headerPanel.add(headerLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        
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

        
        movieListModel = new DefaultListModel<>();
        JList<String> movieList = new JList<>(movieListModel);
        movieList.setFont(new Font("Arial", Font.PLAIN, 16));
        centerPanel.add(new JScrollPane(movieList), BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        
        loadMoviesFromDatabase();

        
        subscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                new Abonnement().setVisible(true);
                dispose();
            }
        });

        searchMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                new Film().setVisible(true);
                dispose();
            }
        });

        rentMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                new Film().setVisible(true);
                dispose();
            }
        });

        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                getContentPane().removeAll();
                addAboutPage();
                revalidate();
                repaint();
            }
        });

       
        add(mainPanel);
    }

    private void loadMoviesFromDatabase() {
        String url = "jdbc:mysql://localhost:3306/al2000"; // URL de la base de données
        String user = "root"; // Nom d'utilisateur de la base de données
        String password = ""; // Mot de passe de la base de données

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT titre FROM films")) {

            movieListModel.clear();
            while (resultSet.next()) {
                String titre = resultSet.getString("titre");
                movieListModel.addElement(titre);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des films: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

   

    private void addRentMoviePage() {
        JPanel rentPanel = new JPanel();
        rentPanel.setBackground(new Color(240, 248, 255));
        rentPanel.setLayout(new BorderLayout());

        JLabel rentLabel = new JLabel("Louer un film", JLabel.CENTER);
        rentLabel.setFont(new Font("Arial", Font.BOLD, 24));
        rentPanel.add(rentLabel, BorderLayout.NORTH);

       

        add(rentPanel);
    }

    private void addAboutPage() {
        JPanel aboutPanel = new JPanel();
        aboutPanel.setBackground(new Color(240, 248, 255));
        aboutPanel.setLayout(new BorderLayout());

        JLabel aboutLabel = new JLabel("À propos", JLabel.CENTER);
        aboutLabel.setFont(new Font("Arial", Font.BOLD, 24));
        aboutPanel.add(aboutLabel, BorderLayout.NORTH);

        JLabel aboutInfo = new JLabel("AL2000 - Location de films et Blu-rays\nDéveloppé par l'équipe AL2000.", JLabel.CENTER);
        aboutInfo.setFont(new Font("Serif", Font.ITALIC, 16));
        aboutPanel.add(aboutInfo, BorderLayout.CENTER);

        add(aboutPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainApp().setVisible(true);
            }
        });
    }
}


