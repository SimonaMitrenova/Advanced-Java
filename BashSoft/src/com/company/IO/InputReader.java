package com.company.io;

import com.company.staticData.SessionData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {
    private static final String END_COMMAND = "quit";

    public static void readCommands() throws Exception {


        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            OutputWriter.writeMessage(String.format("%s>", SessionData.currentPath));
            String input = scanner.readLine().trim();
            if (input.equals(END_COMMAND)){
                break;
            }

            CommandInterpreter.interpretCommand(input);
        }

        for (Thread thread : SessionData.threadPool) {
            thread.join();
        }
    }
}
