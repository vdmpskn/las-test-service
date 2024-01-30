package org.example.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.example.model.question.Question;
import org.example.model.question.Test;

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
public class Mentor extends User{

    public void viewResults(Student student, Test test) {
        System.out.println("Result student " + student.getFirstName() + " " + student.getLastName() + ":");
        System.out.println("Score: " + student.getScore() + "%");

        System.out.println("Student's answers:");
        List<String> answers = student.getAnswers();
        List<Question> questions = test.getQuestions();

        for (int i = 0; i < answers.size(); i++) {
            Question question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getText());
            System.out.println("Student answer: " + answers.get(i));
            System.out.println("Right answer: " + question.getCorrectAnswer());
            System.out.println();
        }
    }

    public void getResultsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (int i = 0; i < 11; i++) {
                reader.readLine();
            }

            String firstName = decrypt(reader.readLine().split(": ")[1]);
            System.out.println("First Name: " + firstName);

            String lastName = decrypt(reader.readLine().split(": ")[1]);
            System.out.println("Last Name: " + lastName);

            String dateTime = reader.readLine().split(": ")[1];
            LocalDateTime.parse(dateTime);
            System.out.println("Date and Time: " + dateTime);

            String score = reader.readLine().split(": ")[1].replace("%", "");
            System.out.println("Score: " + score + "%");

            List<String> answers = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Question")) {
                    String answer = decrypt(reader.readLine().split(": ")[1]);
                    String rightAnswer = decrypt(reader.readLine().split(": ")[1]);
                    answers.add(answer);
                    System.out.println("Student answer: " + answer);
                    System.out.println("Right answer: " + rightAnswer);
                    reader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
