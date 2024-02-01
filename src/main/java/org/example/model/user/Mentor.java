package org.example.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.example.model.question.Test;
import org.example.result.ConsoleResultViewer;
import org.example.result.FileResultReader;
import org.example.result.ResultReader;
import org.example.result.ResultViewer;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class Mentor extends User{
    private final ResultViewer resultViewer = new ConsoleResultViewer();
    private final ResultReader resultReader = new FileResultReader();

    public void viewResults(Student student, Test test){
        resultViewer.view(student,test);
    }

    public void getResultFromFile(String filePath){
        resultReader.read(filePath);
    }
}
