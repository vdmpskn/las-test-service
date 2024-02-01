package org.example.service;

import org.example.model.question.Question;
import org.example.model.question.Test;
import org.example.model.user.Student;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileIOServiceTest {
    @org.junit.jupiter.api.Test
    public void shouldReadQuestionsFromFile() {

        String filePath = "src/main/resources/questions.txt";

        List<Question> questions = FileIOService.readQuestionsFromFile(filePath);

        Question expectedQuestion = new Question("What is the main method in Java?", Arrays.asList("A method that is always called when the program starts", "A method that always exists in every Java class", "A method that can be called multiple times in a program","A method that does not return any value"), "1000");

        assertEquals(10, questions.size());
        assertEquals(expectedQuestion, questions.get(0));
    }

    @org.junit.jupiter.api.Test
    public void testWriteResultsToFile() throws Exception {
        Student student = mock(Student.class);
        Test test = mock(Test.class);

        when(student.getFirstName()).thenReturn("John");
        when(student.getLastName()).thenReturn("Doe");
        when(student.getScore()).thenReturn(100.0);
        when(student.getAnswers()).thenReturn(Arrays.asList("10", "01"));
        when(test.getQuestions()).thenReturn(Arrays.asList(
                new Question("Question 1", Arrays.asList("Option 1", "Option 2"), "10"),
                new Question("Question 2", Arrays.asList("Option 1", "Option 2"), "01")
        ));

        String filePath = "src/main/resources/questions.txt";
        FileIOService.writeResultsToFile(filePath, student, test);

        verify(student, times(1)).getFirstName();
        verify(student, times(1)).getLastName();
        verify(student, times(1)).getScore();
        verify(student, times(1)).getAnswers();
        verify(test, times(1)).getQuestions();
    }

}