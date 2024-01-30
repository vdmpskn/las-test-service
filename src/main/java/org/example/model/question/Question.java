package org.example.model.question;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private final String text;
    private final List<String> options;
    private final String correctAnswer;

}

