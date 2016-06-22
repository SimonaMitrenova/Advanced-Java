package com.company;

import com.company.io.InputReader;
import com.company.io.OutputWriter;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            InputReader.readCommands();
        } catch (IOException e) {
            OutputWriter.displayException(e.getMessage());
        }
    }
}
