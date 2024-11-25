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

public class BlurayAdmin extends JFrame {
    private JTable blurayTable;
    private DefaultTableModel blurayTableModel;

    public BlurayAdmin() {
        setTitle("Gestion des Blu-rays");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        blurayTableModel = new DefaultTableModel(new Object[]{"ID Film", "Nom Film", "Stock Disponible", "Modifier Stock"}, 0);
        blurayTable = new JTable(blurayTableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; 
            }
        };
        loadBlurays();

        blurayTable.getColumn("Modifier Stock").setCellRenderer(new ButtonRenderer("Modifier"));
        blurayTable.getColumn("Modifier Stock").setCellEditor(new ButtonEditor(new JCheckBox(), "Modifier"));

        add(new JScrollPane(blurayTable), BorderLayout.CENTER);

      
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Ajouter un Blu-ray");
        addButton.setFont(new Font("Arial", Font.BOLD, 16));
        addButton.setBackground(new Color(25, 25, 112));
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(e -> showAddBlurayForm());
        buttonPanel.add(addButton);

        JButton filmButton = new JButton("Gestion des Films");
        filmButton.setFont(new Font("Arial", Font.BOLD, 16));
        filmButton.setBackground(new Color(25, 25, 112));
        filmButton.setForeground(Color.WHITE);
        filmButton.addActionListener(e -> {
            new FilmAdmin().setVisible(true);
            dispose();
        });
        buttonPanel.add(filmButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadBlurays() {
        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur";
        String user = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT stockbluray.idFilm, film.titre, stockbluray.stockDisponible FROM stockbluray INNER JOIN film ON stockbluray.idFilm = film.idFilm");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            blurayTableModel.setRowCount(0);
            while (resultSet.next()) {
                blurayTableModel.addRow(new Object[]{
                        resultSet.getLong("idFilm"),
                        resultSet.getString("titre"),
                        resultSet.getInt("stockDisponible"),
                        "Modifier"
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des stocks de Blu-ray: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBlurayStock(long idFilm, int newStock) {
        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur";
        String user = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE stockbluray SET stockDisponible = ? WHERE idFilm = ?")) {

            preparedStatement.setInt(1, newStock);
            preparedStatement.setLong(2, idFilm);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Stock mis à jour avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                loadBlurays();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour du stock: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showAddBlurayForm() {
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.add(new JLabel("Nom Film:"));
        JComboBox<String> filmComboBox = new JComboBox<>();
        loadFilmNames(filmComboBox);
        formPanel.add(filmComboBox);
        formPanel.add(new JLabel("Stock Disponible:"));
        JTextField stockField = new JTextField();
        formPanel.add(stockField);

        int result = JOptionPane.showConfirmDialog(null, formPanel, "Ajouter un Blu-ray", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String selectedFilm = (String) filmComboBox.getSelectedItem();
                long idFilm = getFilmIdByName(selectedFilm);
                int stockDisponible = Integer.parseInt(stockField.getText());
                addBluray(idFilm, stockDisponible);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadFilmNames(JComboBox<String> comboBox) {
        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur";
        String user = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT titre FROM film");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                comboBox.addItem(resultSet.getString("titre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des films: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private long getFilmIdByName(String filmName) {
        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur";
        String user = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT idFilm FROM film WHERE titre = ?")) {
            preparedStatement.setString(1, filmName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("idFilm");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération de l'ID du film: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }

    private void addBluray(long idFilm, int stockDisponible) {
        String url = "jdbc:mysql://127.0.0.1:3306/projet_integrateur";
        String user = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO stockbluray (idFilm, stockDisponible) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setLong(1, idFilm);
            preparedStatement.setInt(2, stockDisponible);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Blu-ray ajouté avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                loadBlurays();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du Blu-ray: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BlurayAdmin().setVisible(true));
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
                long idFilm = (long) blurayTableModel.getValueAt(blurayTable.getSelectedRow(), 0);
                String newStockStr = JOptionPane.showInputDialog(null, "Entrez le nouveau stock disponible:", "Modifier le Stock", JOptionPane.PLAIN_MESSAGE);
                try {
                    int newStock = Integer.parseInt(newStockStr);
                    updateBlurayStock(idFilm, newStock);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
