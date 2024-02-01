package org.example.score;

import org.example.model.question.Question;

import java.util.List;

public class BinaryScoreCalculator implements ScoreCalculator{
    @Override
    public double calculate(List<String> answers, List<Question> questions) {
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

        return ((double) correctCount * 100) / questions.size();
    }
}
