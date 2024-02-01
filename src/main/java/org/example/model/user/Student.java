package org.example.model.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.example.model.question.Question;
import org.example.score.BinaryScoreCalculator;
import org.example.score.ScoreCalculator;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Student extends User {

    private List<String> answers;
    private double score;
    private int attempts;
    private final ScoreCalculator scoreCalculator = new BinaryScoreCalculator();

    public void saveAnswers(List<String> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public void calculateScore(List<Question> questions){
        score = scoreCalculator.calculate(answers, questions);
    }

}

