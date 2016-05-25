package com.company;
import java.io.*;
import java.util.HashMap;

public class WordCount {
    public static void main(String[] args) {
        String wordsPath = "resources\\03_Words.txt";
        String sourcePath = "resources\\03_Input.txt";
        String destinationPath = "resources\\03_Output.txt";
        HashMap<String, Integer> wordsCount = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(wordsPath))) {
            while (true){
                String input = reader.readLine();
                if (input == null){
                    break;
                }
                String[] words = input.toLowerCase().split("\\W+");
                for (String word : words) {
                    if (!wordsCount.containsKey(word)){
                        wordsCount.put(word, 0);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(sourcePath))){
            while (true){
                String input = reader.readLine();
                if (input == null){
                    break;
                }
                String[] words = input.toLowerCase().split("\\W+");
                for (String word : words) {
                    if (wordsCount.containsKey(word)){
                        wordsCount.put(word, wordsCount.get(word) + 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destinationPath))){
            wordsCount.entrySet().stream()
                    .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                    .forEach(e -> {
                        try {
                            writer.write(String.format("%s - %d%n", e.getKey(), e.getValue()));
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
