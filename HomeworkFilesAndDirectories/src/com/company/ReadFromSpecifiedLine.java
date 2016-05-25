package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadFromSpecifiedLine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int linesToSkip = Integer.parseInt(scanner.nextLine());
        String path = "resources\\01_OddLinesIn.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            for (int i = 0; i < linesToSkip; i++) {
                reader.readLine();
            }
            while (true){
                String input = reader.readLine();
                if (input == null){
                    break;
                }
                System.out.println(input);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
