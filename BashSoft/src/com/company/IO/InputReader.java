package com.company.IO;

import com.company.CommandInterpreter;
import com.company.StaticData.SessionData;

import java.util.Scanner;

public class InputReader {
    private static final String endCommand = "quit";

    public static void readCommands(){
        Scanner scanner = new Scanner(System.in);

        while (true){
            OutputWriter.writeMessage(String.format("%s>", SessionData.currentPath));
            String input = scanner.nextLine().trim();

            if (input.equals(endCommand)){
                break;
            }

            CommandInterpreter.interpretCommand(input);
        }
    }
}
