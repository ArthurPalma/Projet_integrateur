package main.java.org.projet.util;

import com.mysql.cj.util.Util;

public class UserSession {
    private static UserSession instance;
    private Utilisateur utilisateur;

    private UserSession() {
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
