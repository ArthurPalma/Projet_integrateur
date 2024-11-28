package main.java.org.projet.util;

public class PasswordUtil {
    private static final int LOG_ROUNDS = 12;
    
    public static String hash(String password) {
        try {
            String salt = BCrypt.gensalt(LOG_ROUNDS);
            String hashedPassword = BCrypt.hashpw(password, salt);
            
            return hashedPassword;
            
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean comparePassword(String inputPassword, String storedPassword) {
        try {
            return BCrypt.checkpw(inputPassword, storedPassword);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
