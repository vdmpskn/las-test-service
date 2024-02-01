package org.example.score;

import org.example.model.question.Question;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinaryScoreCalculatorTest {
    @Test
    public void testCalculatePass() {
        BinaryScoreCalculator binaryScoreCalculator = new BinaryScoreCalculator();

        List<String> answers = Arrays.asList("10", "01");
        List<Question> questions = Arrays.asList(
                new Question("Question 1", Arrays.asList("Option 1", "Option 2"), "10"),
                new Question("Question 2", Arrays.asList("Option 1", "Option 2"), "01")
        );

        double result = binaryScoreCalculator.calculate(answers, questions);

        assertEquals(100.0, result);
    }

    @Test
    public void testCalculateFail() {
        BinaryScoreCalculator binaryScoreCalculator = new BinaryScoreCalculator();

        List<String> answers = Arrays.asList("10", "01");
        List<Question> questions = Arrays.asList(
                new Question("Question 1", Arrays.asList("Option 1", "Option 2"), "01"),
                new Question("Question 2", Arrays.asList("Option 1", "Option 2"), "10")
        );

        double result = binaryScoreCalculator.calculate(answers, questions);

        assertEquals(0.0, result);
    }

}