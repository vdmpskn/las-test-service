package org.example.model.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Question {
    private String text;
    private List<String> options;
    private  String correctAnswer;
}

