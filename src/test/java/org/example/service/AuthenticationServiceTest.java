package org.example.service;

import org.example.model.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AuthenticationServiceTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void shouldAuthenticate_Success() {
        AuthenticationService authenticationService = new AuthenticationService();
        User user = authenticationService.authenticate("student1", "pass2");

        assertEquals("Login successful\n", outContent.toString());
        assertEquals("student1", user.getUsername());
    }

    @Test
    void shouldAuthenticate_Failure() {
        AuthenticationService authenticationService = new AuthenticationService();
        User user = authenticationService.authenticate("student1", "wrongpass");

        assertEquals("Password comparison failed\nLogin failed\n", outContent.toString());
        assertNull(user);
    }

}