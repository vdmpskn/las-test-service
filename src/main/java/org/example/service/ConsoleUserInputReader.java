package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.service.UserInputReader;

import java.util.Scanner;

@RequiredArgsConstructor
public class ConsoleUserInputReader implements UserInputReader {
    private final Scanner scanner;

    @Override
    public String readUserInput() {
        return scanner.nextLine();
    }
}
