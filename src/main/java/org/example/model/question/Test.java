package org.example.model.question;

import lombok.Data;
import org.example.model.user.Student;
import org.example.service.UserInputReader;

import java.util.*;
import java.util.concurrent.*;

@Data
public class Test implements ITest{
    private final List<Question> questions;
    private final int timePerQuestion;
    private final UserInputReader userInputReader;

    public Test(List<Question> questions, int timePerQuestion, UserInputReader userInputReader) {
        this.questions = questions;
        this.timePerQuestion = timePerQuestion;
        this.userInputReader = userInputReader;
    }

    @Override
    public Student conductTest(Student student) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        int studentAttempts = student.getAttempts();

        if (studentAttempts > 0) {
            System.out.println("Test is started. You have " + timePerQuestion + " seconds for each question.");
            System.out.println("You have " + studentAttempts + " attempts for completing this test.");
            studentAttempts--;
            List<String> answers = new ArrayList<>();

            for (int i = 0; i < questions.size(); i++) {
                Question question = questions.get(i);
                displayQuestion(i, question);
                String studentAnswer = getStudentAnswer(executorService);
                String answerMaskString = processStudentAnswer(studentAnswer, question);
                answers.add(answerMaskString);
            }

            student.saveAnswers(answers);
            student.calculateScore(questions);

            System.out.println("Test is completed. Your result is : " + student.getScore() + "%");
            System.out.println("Attempts left: " + studentAttempts);

            executorService.shutdown();
        } else {
            System.out.println("You have no attempts");
            System.exit(0);
        }
        return student;
    }

    private void displayQuestion(int i, Question question) {
        System.out.println("Question " + (i + 1) + ": " + question.getText());
        List<String> options = question.getOptions();
        for (int j = 0; j < options.size(); j++) {
            System.out.println((char) ('A' + j) + ". " + options.get(j));
        }
    }

    private String getStudentAnswer(ExecutorService executorService) {
        String studentAnswer;
        try {
            Future<String> future = executorService.submit(() -> {
                System.out.print("Write your answer(s) (separated by commas if multiple): ");
                return userInputReader.readUserInput();
            });

            studentAnswer = future.get(timePerQuestion, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println("Time is up! Moving to the next question.");
            studentAnswer = "";
        }
        return studentAnswer;
    }

    private String processStudentAnswer(String studentAnswer, Question question) {
        int answerMask = 0;
        for (String answer : studentAnswer.split(",")) {
            int index = answer.trim().toUpperCase().charAt(0) - 'A';
            answerMask |= 1 << index;
        }

        String answerMaskString = Integer.toBinaryString(answerMask);
        while (answerMaskString.length() < question.getCorrectAnswer().length()) {
            answerMaskString = "0" + answerMaskString;
        }
        answerMaskString = new StringBuilder(answerMaskString).reverse().toString();

        return answerMaskString;
    }
}
