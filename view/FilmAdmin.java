package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmAdmin extends JFrame {
    private JTextField titreField;
    private JTextField realisateurField;
    private JTextField acteurField;
    private JTextField genreField;
    private JTextField couvertureField;
    private JTable filmTable;
    private DefaultTableModel filmTableModel;

    public FilmAdmin() {
        setTitle("Gestion des Films");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        filmTableModel = new DefaultTableModel(new Object[]{"ID", "Titre", "Réalisateur", "Acteur(s)", "Genre", "Couverture", "Modifier", "Supprimer"}, 0);
        filmTable = new JTable(filmTableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6 || column == 7;
                }
        };
        loadFilms();

        filmTable.getColumn("Modifier").setCellRenderer(new ButtonRenderer("✍"));
        filmTable.getColumn("Modifier").setCellEditor(new ButtonEditor(new JCheckBox(), "Modifier"));

        filmTable.getColumn("Supprimer").setCellRenderer(new ButtonRenderer("❌"));
        filmTable.getColumn("Supprimer").setCellEditor(new ButtonEditor(new JCheckBox(), "Supprimer"));

        add(new JScrollPane(filmTable), BorderLayout.CENTER);

      
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Ajouter un film");
        addButton.setFont(new Font("Arial", Font.BOLD, 16));
        addButton.setBackground(new Color(25, 25, 112));
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(e -> showFilmForm(-1));
        buttonPanel.add(addButton);

        JButton blurayButton = new JButton("Liste Bluray");
        blurayButton.setFont(new Font("Arial", Font.BOLD, 16));
        blurayButton.setBackground(new Color(25, 25, 112));
        blurayButton.setForeground(Color.WHITE);
        blurayButton.addActionListener(e -> {
            new BlurayAdmin().setVisible(true);
            dispose();
        });
        buttonPanel.add(blurayButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadFilms() {
        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur";
        String user = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM film");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            filmTableModel.setRowCount(0);
            while (resultSet.next()) {
                filmTableModel.addRow(new Object[]{
                        resultSet.getLong("idFilm"),
                        resultSet.getString("titre"),
                        resultSet.getString("realisateur"),
                        resultSet.getString("acteur"),
                        resultSet.getString("genre"),
                        resultSet.getString("couverture"),
                        "✍",
                        "❌"
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des films: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showFilmForm(long idFilm) {
        String titre = "", realisateur = "", acteur = "", genre = "", couverture = "";
        boolean isUpdate = (idFilm != -1);

        if (isUpdate) {
            String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur";
            String user = "root";
            String dbPassword = "";
            try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM film WHERE idFilm = ?")) {
                preparedStatement.setLong(1, idFilm);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    titre = resultSet.getString("titre");
                    realisateur = resultSet.getString("realisateur");
                    acteur = resultSet.getString("acteur");
                    genre = resultSet.getString("genre");
                    couverture = resultSet.getString("couverture");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la récupération du film: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.add(new JLabel("Titre:"));
        JTextField titreField = new JTextField(titre);
        formPanel.add(titreField);
        formPanel.add(new JLabel("Réalisateur:"));
        JTextField realisateurField = new JTextField(realisateur);
        formPanel.add(realisateurField);
        formPanel.add(new JLabel("Acteur(s):"));
        JTextField acteurField = new JTextField(acteur);
        formPanel.add(acteurField);
        formPanel.add(new JLabel("Genre:"));
        JTextField genreField = new JTextField(genre);
        formPanel.add(genreField);
        formPanel.add(new JLabel("Couverture (Chemin):"));
        JTextField couvertureField = new JTextField(couverture);
        formPanel.add(couvertureField);

        int result = JOptionPane.showConfirmDialog(null, formPanel, (isUpdate ? "Modifier le film" : "Ajouter un film"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String titreValue = titreField.getText();
            String realisateurValue = realisateurField.getText();
            String acteurValue = acteurField.getText();
            String genreValue = genreField.getText();
            String couvertureValue = couvertureField.getText();

            if (titreValue.isEmpty() || realisateurValue.isEmpty() || acteurValue.isEmpty() || genreValue.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (isUpdate) {
                updateFilm(idFilm, titreValue, realisateurValue, acteurValue, genreValue, couvertureValue);
            } else {
                addFilm(titreValue, realisateurValue, acteurValue, genreValue, couvertureValue);
            }
        }
    }

    private void addFilm(String titre, String realisateur, String acteur, String genre, String couverture) {
        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur";
        String user = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO film (titre, realisateur, acteur, genre, couverture) VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, titre);
            preparedStatement.setString(2, realisateur);
            preparedStatement.setString(3, acteur);
            preparedStatement.setString(4, genre);
            preparedStatement.setString(5, couverture);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Film ajouté avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                loadFilms();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du film: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateFilm(long idFilm, String titre, String realisateur, String acteur, String genre, String couverture) {
        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur";
        String user = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE film SET titre = ?, realisateur = ?, acteur = ?, genre = ?, couverture = ? WHERE idFilm = ?")) {

            preparedStatement.setString(1, titre);
            preparedStatement.setString(2, realisateur);
            preparedStatement.setString(3, acteur);
            preparedStatement.setString(4, genre);
            preparedStatement.setString(5, couverture);
            preparedStatement.setLong(6, idFilm);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Film modifié avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                loadFilms();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la modification du film: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteFilm(long idFilm) {
        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur";
        String user = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM film WHERE idFilm = ?")) {

            preparedStatement.setLong(1, idFilm);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Film supprimé avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                loadFilms();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la suppression du film: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FilmAdmin().setVisible(true));
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer(String text) {
            setOpaque(true);
            setText(text);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox, String actionType) {
            super(checkBox);
            button = new JButton(actionType);
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                String action = label.split(" ")[0];
                long idFilm = (long) filmTableModel.getValueAt(filmTable.getSelectedRow(), 0);
                if (action.equals("✍")) {
                    showFilmForm(idFilm);
                } else if (action.equals("❌")) {
                    deleteFilm(idFilm);
                }
            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }
}
