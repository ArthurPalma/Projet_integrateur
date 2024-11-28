package main.java.org.projet.service;

import main.java.org.projet.dao.UtilisateurAbonneDAO;
import main.java.org.projet.model.UtilsateurAbonne;
import main.java.org.projet.util.PasswordUtil;
import main.java.org.projet.util.UserSession;

public class LoginService {
    
    private final UtilisateurAbonneDAO utilisateurAbonneDAO;
    
    public LoginService() {
        utilisateurAbonneDAO = new UtilisateurAbonneDAO();
    }

    /**
     * Authenticate a user based on the provided username and password.
     * 
     * @param username the username to authenticate
     * @param inputPassword the input password to verify
     * @return -1 if authentication fails, 1 if successful
     */
    public Integer authenticate(String username, String inputPassword) {
        UtilsateurAbonne utilisateur = utilisateurAbonneDAO.findByUsername(username);

        if (utilisateur == null ) {
            return -1;
        }
        
        boolean passwordMatch = PasswordUtil.comparePassword(inputPassword, utilisateur.getPassword());
        
        if (!passwordMatch) {
            return -1;
        }

        UserSession.getInstance().setUtilisateur(utilisateur);

        return 1;
    }
}
