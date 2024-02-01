package org.example.score;

import org.example.model.question.Question;

import java.util.List;

public interface ScoreCalculator {
    double calculate(List<String> answers, List<Question> questions);
}
