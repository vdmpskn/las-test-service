package org.example.service;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHash {
    public static String generateStrongPasswordHash(String password) {
       String salt = BCrypt.gensalt();

        return BCrypt.hashpw(password, salt);
    }

    public static boolean validatePassword(String enteredPassword, String storedHash) {
        return BCrypt.checkpw(enteredPassword, storedHash);
    }
}
