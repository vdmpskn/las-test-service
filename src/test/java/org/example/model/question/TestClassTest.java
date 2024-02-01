package org.example.model.question;

import org.example.model.user.Student;
import org.example.service.UserInputReader;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestClassTest {

    @org.junit.jupiter.api.Test
    public void testConductTestSuccess() {
        UserInputReader userInputReader = mock(UserInputReader.class);
        when(userInputReader.readUserInput()).thenReturn("A", "B");

        List<Question> questions = Arrays.asList(
                new Question("Question 1", Arrays.asList("Option 1", "Option 2"), "1000"),
                new Question("Question 2", Arrays.asList("Option 1", "Option 2"), "0100")
        );
        Test test = new Test(questions, 10, userInputReader);

        Student student = new Student();
        student.setAttempts(1);

        Student resultStudent = test.conductTest(student);

        assertEquals(100.0, resultStudent.getScore());
    }

    @org.junit.jupiter.api.Test
    public void testConductTestFailure() {
        UserInputReader userInputReader = mock(UserInputReader.class);
        when(userInputReader.readUserInput()).thenReturn("A", "A");

        List<Question> questions = Arrays.asList(
                new Question("Question 1", Arrays.asList("Option 1", "Option 2"), "10"),
                new Question("Question 2", Arrays.asList("Option 1", "Option 2"), "01")
        );
        Test test = new Test(questions, 10, userInputReader);

        Student student = new Student();
        student.setAttempts(1);

        Student resultStudent = test.conductTest(student);

        assertTrue(resultStudent.getScore() < 100.0);
    }

    @org.junit.jupiter.api.Test
    public void testConductTestNoAttempts() {
        UserInputReader userInputReader = mock(UserInputReader.class);

        List<Question> questions = Arrays.asList(
                new Question("Question 1", Arrays.asList("Option 1", "Option 2"), "10"),
                new Question("Question 2", Arrays.asList("Option 1", "Option 2"), "01")
        );
        Test test = new Test(questions, 10, userInputReader);

        Student student = new Student();
        student.setAttempts(0);

        assertThrows(RuntimeException.class, () -> {
            test.conductTest(student);
        });
    }
}