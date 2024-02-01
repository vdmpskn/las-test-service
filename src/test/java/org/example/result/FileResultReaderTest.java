package org.example.result;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class FileResultReaderTest {
    @TempDir
    Path tempDir;

    @Test
    void testReadValidFile() {
        FileResultReader resultReader = new FileResultReader();
        String filePath = "src/main/resources/questions.txt";

        assertDoesNotThrow(() -> resultReader.read(filePath));
    }

    @Test
    void readFileWithMissingData() throws IOException {
        String filePath = createTestFile("John:: :: 95%: Question 1: Answer 1: Right Answer 1");
        FileResultReader reader = new FileResultReader();

        assertThrows(RuntimeException.class, () -> reader.read(filePath));
    }

    @Test
    void readFileWithInvalidLineCount() throws IOException {
        String filePath = createTestFile("John: Doe: 2024-01-31T22:47:00: 95%");
        FileResultReader reader = new FileResultReader();

        assertThrows(RuntimeException.class, () -> reader.read(filePath));
    }

    @Test
    void readFileWithInvalidDateTime() throws IOException {
        String filePath = createTestFile("John: Doe: InvalidDateTime: 95%: Question 1: Answer 1: Right Answer 1");
        FileResultReader reader = new FileResultReader();

        assertThrows(RuntimeException.class, () -> reader.read(filePath));
    }

    @Test
    void readFileWithInvalidScore() throws IOException {
        String filePath = createTestFile("John: Doe: 2024-01-31T22:47:00: InvalidScore: Question 1: Answer 1: Right Answer 1");
        FileResultReader reader = new FileResultReader();

        assertThrows(RuntimeException.class, () -> reader.read(filePath));
    }

    private String createTestFile(String content) throws IOException {
        File file = tempDir.resolve("test.txt").toFile();
        Files.write(file.toPath(), content.getBytes());
        return file.getAbsolutePath();
    }
}