package com.company;

import com.company.IO.InputReader;
import com.company.IO.OutputWriter;

import java.io.IOException;

public class Program {

    public static void main(String[] args) {
        try{
            InputReader.readCommands();
        } catch (IOException e) {
            OutputWriter.displayException(e.getMessage());
        }
    }
}
