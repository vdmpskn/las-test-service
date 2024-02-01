package org.example.service;

import org.example.model.user.Mentor;
import org.example.model.user.Student;
import org.example.model.user.User;

import java.util.HashMap;

public class AuthenticationService {

    private HashMap<String, User> addUsers() {
        HashMap<String, User> users = new HashMap<>();

        Student student = new Student();
        student.setID(2L);
        student.setUsername("student1");
        student.setPassword(PasswordHash.generateStrongPasswordHash("pass2"));
        student.setRole("student");
        student.setFirstName("John");
        student.setLastName("Rio");

        Mentor mentor = new Mentor();
        mentor.setID(1L);
        mentor.setUsername("mentor1");
        mentor.setPassword(PasswordHash.generateStrongPasswordHash("pass1"));
        mentor.setRole("mentor");
        mentor.setFirstName("Alex");
        mentor.setLastName("Berlin");

        users.put(student.getUsername(), student);
        users.put(mentor.getUsername(), mentor);

        return users;
    }

    public User authenticate(String username, String password) {
        HashMap<String, User> userHashMap = addUsers();

        User retrievedUser = userHashMap.get(username);
        if (retrievedUser != null) {
            if (PasswordHash.validatePassword(password, retrievedUser.getPassword())) {
                System.out.println("Login successful");
                return retrievedUser;
            } else {
                System.out.println("Password comparison failed");
            }
        } else {
            System.out.println("User not found");
        }

        System.out.println("Login failed");
        return null;
    }

}
