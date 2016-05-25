package com.company;

import java.io.*;

public class LineNumbers {
    public static void main(String[] args) {
        String inputPath = "resources\\01_OddLinesIn.txt";
        String outputPath = "resources\\02_LineNumbersOut.txt";
        int lineCounter = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            while (true){
                String inputLine = reader.readLine();
                if (inputLine == null){
                    break;
                }
                writer.write(String.format("%d. %s%n", lineCounter++, inputLine));
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
