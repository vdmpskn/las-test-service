package org.example.model.user;

import lombok.Data;

@Data
public abstract class User {

    Long iD;

    String username;

    String password;

    String firstName;

    String lastName;

    String role;

}
