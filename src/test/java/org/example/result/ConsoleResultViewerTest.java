package org.example.result;

import org.example.model.question.Question;
import org.example.model.question.Test;
import org.example.model.user.Student;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ConsoleResultViewerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Mock
    private Student student;

    @Mock
    private Test test;

    @Mock
    private Question question;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        System.setOut(new PrintStream(outContent));
    }

    @org.junit.jupiter.api.Test
    public void shouldViewTestResult() {
        ConsoleResultViewer consoleResultViewer = new ConsoleResultViewer();
        when(student.getFirstName()).thenReturn("John");
        when(student.getLastName()).thenReturn("Doe");
        when(student.getScore()).thenReturn(85.0);
        when(student.getAnswers()).thenReturn(Arrays.asList("A", "B", "C"));
        when(test.getQuestions()).thenReturn(Arrays.asList(question, question, question));
        when(question.getText()).thenReturn("Sample Question");
        when(question.getCorrectAnswer()).thenReturn("A");

        consoleResultViewer.view(student, test);

        String expectedOutput = "Result student John Doe:\n" +
                "Score: 85.0%\n" +
                "Student's answers:\n" +
                "Question 1: Sample Question\n" +
                "Student answer: A\n" +
                "Right answer: A\n\n" +
                "Question 2: Sample Question\n" +
                "Student answer: B\n" +
                "Right answer: A\n\n" +
                "Question 3: Sample Question\n" +
                "Student answer: C\n" +
                "Right answer: A\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}