package org.example;

import org.example.model.question.Question;
import org.example.model.question.Test;
import org.example.model.user.Mentor;
import org.example.model.user.Student;
import org.example.service.*;

import java.util.List;
import java.util.Scanner;

public class Application {


    public static void main(String[] args) {
        String filePath = args[0];
        List<Question> questions = FileIOService.readQuestionsFromFile(filePath);
        Scanner sc = new Scanner(System.in);

        UserInputReader userInputReader = new ConsoleUserInputReader(sc);

        Test test = new Test(questions, 60, userInputReader);
        AuthenticationService authenticationService = new AuthenticationService();


        String usernameMentor = "mentor1";
        String passwordMentor = "pass1";

        String usernameStudent = "student1";
        String passwordStudent = "pass2";

        Mentor mentor = (Mentor) authenticationService.authenticate(usernameMentor, passwordMentor);
        Student student = (Student) authenticationService.authenticate(usernameStudent, passwordStudent);

        try {
            test.conductTest(student);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        mentor.viewResults(student, test);

        mentor.getResultFromFile("src/main/resources/questions.txt");

    }
}

