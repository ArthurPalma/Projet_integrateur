package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Abonnement extends JFrame{

        public Abonnement() {
        setTitle("Espace Abonnement");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel subscriptionPanel = new JPanel();
        subscriptionPanel.setBackground(new Color(240, 248, 255));
        subscriptionPanel.setLayout(new BorderLayout());

        JLabel subscriptionLabel = new JLabel("Espace Abonnement", JLabel.CENTER);
        subscriptionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        subscriptionPanel.add(subscriptionLabel, BorderLayout.NORTH);

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBackground(new Color(240, 248, 255));

        JButton loginButton = new JButton("Se connecter");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 18));
        JButton createSubscriptionButton = new JButton("Créer un abonnement");
        createSubscriptionButton.setFont(new Font("Arial", Font.PLAIN, 18));

        buttonPanel.add(loginButton);
        buttonPanel.add(createSubscriptionButton);

        subscriptionPanel.add(buttonPanel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login().setVisible(true);
                dispose();
            }
        });

        createSubscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Page de création d'abonnement en cours de développement.", "Créer un abonnement", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        add(subscriptionPanel);
    }
    
}
