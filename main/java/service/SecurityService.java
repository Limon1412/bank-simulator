package service;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecurityService{
    public static String hashPassword(String password, String salt){
        try {
            String combined = password + salt;
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(combined.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException exception){
            System.err.println("Алгоритм хэширования не доступен " + exception.getMessage());
        }
        return null;
    }
}
