package com.company;

import java.io.*;

public class OddLines {

    public static void main(String[] args) {
        String inputPath = "resources\\01_OddLinesIn.txt";
        String outputPath = "resources\\01_OddLinesOut.txt";
        int lineCounter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))){

            while (true){
                String inputLine = reader.readLine();
                if (inputLine == null){
                    break;
                }
                if (lineCounter % 2 == 1){
                    writer.write(inputLine);
                    writer.newLine();
                }
                lineCounter++;
            }

        } catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
