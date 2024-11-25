package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Film extends JFrame {
    private DefaultListModel<String> searchResultsModel;

    public Film() {
        setTitle("Rechercher un Film");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(240, 248, 255));
        searchPanel.setLayout(new BorderLayout(10, 10));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel searchLabel = new JLabel("Rechercher un film", JLabel.CENTER);
        searchLabel.setFont(new Font("Arial", Font.BOLD, 24));
        searchLabel.setForeground(new Color(25, 25, 112));
        searchPanel.add(searchLabel, BorderLayout.NORTH);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BorderLayout());
        fieldsPanel.setBackground(new Color(240, 248, 255));

        JTextField searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        fieldsPanel.add(searchField, BorderLayout.CENTER);

        searchPanel.add(fieldsPanel, BorderLayout.NORTH);

        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(new Color(240, 248, 255));

        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        searchPanel.add(scrollPane, BorderLayout.CENTER);

        add(searchPanel, BorderLayout.CENTER);

        // Load all movies at the start
        loadAllMovies(resultsPanel);

        // Add DocumentListener for reactive search
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchMoviesFromDatabase(searchField.getText(), resultsPanel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchMoviesFromDatabase(searchField.getText(), resultsPanel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchMoviesFromDatabase(searchField.getText(), resultsPanel);
            }
        });

       
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

    private void loadAllMovies(JPanel resultsPanel) {
        searchMoviesFromDatabase("", resultsPanel);
    }

    private void searchMoviesFromDatabase(String searchText, JPanel resultsPanel) {
        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur";
        String user = "root";
        String dbPassword = ""; 
        String query = "SELECT * FROM film WHERE titre LIKE ? OR acteur LIKE ? OR realisateur LIKE ?";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + searchText + "%");
            preparedStatement.setString(2, "%" + searchText + "%");
            preparedStatement.setString(3, "%" + searchText + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            resultsPanel.removeAll();
            while (resultSet.next()) {
                FilmItem filmItem = new FilmItem(
                        resultSet.getLong("idFilm"),
                        resultSet.getString("titre"),
                        resultSet.getString("acteur"),
                        resultSet.getString("realisateur"),
                        resultSet.getString("couverture")
                );

                JPanel filmCard = createFilmCard(filmItem);
                resultsPanel.add(filmCard);
                resultsPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
            }
            resultsPanel.revalidate();
            resultsPanel.repaint();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la recherche des films: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createFilmCard(FilmItem filmItem) {
        JPanel filmCard = new JPanel();
        filmCard.setLayout(new BorderLayout(10, 10));
        filmCard.setBackground(new Color(255, 255, 255));
        filmCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        
        JLabel thumbnail;
        if (filmItem.getCouverture() != null && !filmItem.getCouverture().isEmpty()) {
            ImageIcon imageIcon = new ImageIcon(filmItem.getCouverture());
            Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            thumbnail = new JLabel(new ImageIcon(image));
        } else {
          
            thumbnail = new JLabel(new ImageIcon("placeholder.png"));
        }
        thumbnail.setPreferredSize(new Dimension(100, 100));
        filmCard.add(thumbnail, BorderLayout.WEST);

        String filmInfo = "<html><b>" + filmItem.getTitre() + "</b><br>Acteur : " + filmItem.getActeur() + "<br>Réalisateur : " + filmItem.getRealisateur() + "</html>";
        JLabel filmDetails = new JLabel(filmInfo);
        filmDetails.setFont(new Font("Arial", Font.PLAIN, 14));
        filmCard.add(filmDetails, BorderLayout.CENTER);

        JButton rentButton = new JButton("Louer");
        rentButton.setFont(new Font("Arial", Font.BOLD, 14));
        rentButton.setBackground(new Color(25, 25, 112));
        rentButton.setForeground(Color.WHITE);
        rentButton.addActionListener(e -> {
            long utilisateurId = 1; 
            new Location(filmItem.getTitre(), utilisateurId, filmItem.getIdFilm()).setVisible(true);
            dispose();
        });
        filmCard.add(rentButton, BorderLayout.EAST);

        return filmCard;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Film().setVisible(true));
    }
}

class FilmItem {
    private long idFilm;
    private String titre;
    private String acteur;
    private String realisateur;
    private String couverture;

    public FilmItem(long idFilm, String titre, String acteur, String realisateur, String couverture) {
        this.idFilm = idFilm;
        this.titre = titre;
        this.acteur = acteur;
        this.realisateur = realisateur;
        this.couverture = couverture;
    }

    public long getIdFilm() {
        return idFilm;
    }

    public String getTitre() {
        return titre;
    }

    public String getActeur() {
        return acteur;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public String getCouverture() {
        return couverture;
    }
}
