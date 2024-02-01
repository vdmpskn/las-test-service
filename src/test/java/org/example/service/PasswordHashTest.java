package org.example.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHashTest {

    @Test
    void shouldPasswordHash_Success(){
        String password = "testPassword";
        String passwordHash = PasswordHash.generateStrongPasswordHash(password);

        boolean correctPasswordResult = PasswordHash.validatePassword(password, passwordHash);
        assertTrue(correctPasswordResult, "The correct password should validate to true");
    }

    @Test
    void shouldPasswordHash_Failure(){
        String password = "testPassword";
        String passwordHash = PasswordHash.generateStrongPasswordHash(password);

        boolean incorrectPasswordResult = PasswordHash.validatePassword("wrongPassword", passwordHash);
        assertFalse(incorrectPasswordResult, "An incorrect password should validate to false");
    }
}