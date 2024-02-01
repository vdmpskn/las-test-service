package org.example.result;

import org.example.model.question.Question;
import org.example.model.question.Test;
import org.example.model.user.Student;

import java.util.List;

public class ConsoleResultViewer implements ResultViewer{

    @Override
    public void view(Student student, Test test) {
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
}
