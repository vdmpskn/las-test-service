package org.example.result;

import org.example.model.question.Test;
import org.example.model.user.Student;

public interface ResultViewer {
    void view(Student student, Test test);
}
