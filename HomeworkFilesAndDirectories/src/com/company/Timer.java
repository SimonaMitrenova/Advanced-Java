package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Timer {
    public static void main(String[] args) {
        try (BufferedReader firstReader = new BufferedReader(new FileReader("resources\\01_OddLinesIn.txt"));
             BufferedReader secondReader = new BufferedReader(new FileReader("resources\\01_OddLinesOut.txt"))) {
            long startTime = System.nanoTime();
            while ((firstReader.readLine()) != null){

            }
            long firstDuration = System.nanoTime() - startTime;

            while ((secondReader.readLine()) != null){

            }
            long secondDuration = System.nanoTime() - (startTime + firstDuration);

            if (firstDuration < secondDuration){
                System.out.println("True");
            } else {
                System.out.println("False");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
