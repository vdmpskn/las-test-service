package org.example.service;

import org.example.model.question.Question;
import org.example.model.question.Test;
import org.example.model.user.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileIOService {

    public static List<Question> readQuestionsFromFile(String filePath) {
        List<Question> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 4) {
                    String questionText = parts[1];
                    String correctAnswers = parts[2];
                    List<String> options = new ArrayList<>();
                    for (int i = 3; i < parts.length; i++) {
                        options.add(parts[i]);
                    }
                    questions.add(new Question(questionText, options, correctAnswers));
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file:" + e.getMessage());
            e.printStackTrace();
        }

        return questions;
    }

    public static void writeResultsToFile(String filePath, Student student, Test test) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            String encryptedFirstName = EncryptionService.encrypt(student.getFirstName());
            String encryptedLastName = EncryptionService.encrypt(student.getLastName());

            writer.write("First Name: " + encryptedFirstName + "\n");
            writer.write("Last Name: " + encryptedLastName + "\n");
            writer.write("Date and time: " + LocalDateTime.now() + "\n");
            writer.write("Result: " + student.getScore() + "%\n");

            List<String> answers = student.getAnswers();
            List<Question> questions = test.getQuestions();

            for (int i = 0; i < answers.size(); i++) {
                Question question = questions.get(i);
                String encryptedAnswer = EncryptionService.encrypt(answers.get(i));

                String encryptedRightAnswer = EncryptionService.encrypt(question.getCorrectAnswer());

                writer.write("Question " + (i + 1) + ": " + question.getText() + "\n");
                writer.write("Student answer: " + encryptedAnswer + "\n");
                writer.write("Right answer: " + encryptedRightAnswer + "\n");
                writer.write("\n");
            }

            writer.write("\n");
        } catch (Exception e) {
            System.out.println("An error occurred while writing to the file:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
