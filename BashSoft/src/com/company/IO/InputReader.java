package com.company.IO;

import com.company.CommandInterpreter;
import com.company.StaticData.SessionData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class InputReader {
    private static final String endCommand = "quit";

    public static void readCommands() throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            OutputWriter.writeMessage(String.format("%s>", SessionData.currentPath));
            String input = scanner.readLine().trim();

            if (input.equals(endCommand)){
                break;
            }

            CommandInterpreter.interpretCommand(input);
        }

        Thread[] threads = new Thread[Thread.activeCount()];
        Thread.enumerate(threads);
        for (Thread thread : threads) {
            try{
                if (!thread.getName().equals("main")){
                    thread.join();
                }
            } catch (InterruptedException e) {
                OutputWriter.displayException(e.getMessage());
            }
        }
    }
}
