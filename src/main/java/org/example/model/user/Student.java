package org.example.model.user;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.example.model.question.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.example.service.EncryptionService.decrypt;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class Student extends User {

    private List<String> answers;

    private double score;

    private int attempts;

    public void saveAnswers(List<String> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public void calculateScore(List<Question> questions) {
        int correctCount = 0;

        for (int i = 0; i < questions.size(); i++) {
            String studentAnswer = answers.get(i);
            String correctAnswers = questions.get(i).getCorrectAnswer();

            int studentAnswerMask = Integer.parseInt(studentAnswer, 2);
            int correctAnswersMask = Integer.parseInt(correctAnswers, 2);

            if ((studentAnswerMask & correctAnswersMask) == correctAnswersMask) {
                correctCount++;
            }
        }

        score = ((double) correctCount * 100) / questions.size();
    }

}

