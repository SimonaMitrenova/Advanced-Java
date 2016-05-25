package com.company;

import java.io.*;

public class MergingTwoFilesIntoThirdOne {
    public static void main(String[] args) {
        String source1Path = "resources\\04_Input1.txt";
        String source2Path = "resources\\04_Input2.txt";
        String mergedPath = "resources\\04_Merged.txt";

        try (BufferedReader readerFirst = new BufferedReader(new FileReader(source1Path));
             BufferedReader readerSecond = new BufferedReader(new FileReader(source2Path));
             BufferedWriter writer = new BufferedWriter(new FileWriter(mergedPath))) {

            while (true){
                String firstInput = readerFirst.readLine();
                if (firstInput != null){
                    writer.write(firstInput);
                    writer.newLine();
                }
                String secondInput = readerSecond.readLine();
                if (secondInput != null){
                    writer.write(secondInput);
                    writer.newLine();
                }

                if (firstInput == null && secondInput == null){
                    break;
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
