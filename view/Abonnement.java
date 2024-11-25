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
        
      
        JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton returnButton = new JButton("Retour à l'accueil");
        returnButton.setFont(new Font("Arial", Font.BOLD, 16));
        returnButton.setBackground(new Color(25, 25, 112));
        returnButton.setForeground(Color.WHITE);
        returnButton.addActionListener(e -> {
            new Main().setVisible(true);
            dispose();
        });
        buttonPanel1.add(returnButton);

        add(buttonPanel1, BorderLayout.SOUTH);
    

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
                new Inscription().setVisible(true);
                dispose();}  });
        

        add(subscriptionPanel);
    }
    
}
