package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class TargetPractice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];
        String word = scanner.nextLine();
        char[][] matrix = new char[rows][cols];
        int wordIndex = 0;

        boolean isEven = true;
        for (int row = rows - 1; row >= 0; row--) {
            if (isEven){
                for (int col = cols - 1; col >= 0; col--) {
                    matrix[row][col] = word.charAt(wordIndex);
                    wordIndex = (wordIndex + 1) % word.length();
                }
            } else{
                for (int col = 0; col < cols; col++) {
                    matrix[row][col] = word.charAt(wordIndex);
                    wordIndex = (wordIndex + 1) % word.length();
                }
            }

            isEven = !isEven;
        }

        int[] shotArgs = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int shotRow = shotArgs[0];
        int shotCol = shotArgs[1];
        int shotRadius = shotArgs[2];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                boolean isInRange = Math.pow(shotRow - row, 2) + Math.pow(shotCol - col, 2) <= Math.pow(shotRadius, 2);
                if (isInRange){
                    matrix[row][col] = ' ';
                }
            }
        }

        for (int col = 0; col < cols; col++) {
            for (int row = rows - 1; row >= 0; row--) {
                if (matrix[row][col] == ' '){
                    int rowToSlide = row - 1;
                    while (matrix[row][col] == ' ' && rowToSlide >= 0){
                        matrix[row][col] = matrix[rowToSlide][col];
                        matrix[rowToSlide][col] = ' ';
                        rowToSlide--;
                    }
                }
            }
        }

        printMatrix(matrix);
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.printf("%s", matrix[row][col]);
            }
            System.out.println();
        }
    }
}