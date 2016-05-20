package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class RubiksMatrix {

    static int[][] matrix;
    static int rows;
    static int cols;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        rows = dimensions[0];
        cols = dimensions[1];
        matrix = new int[rows][cols];
        int counter = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                matrix[r][c] = counter;
                counter++;
            }
        }
        int inputCount = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < inputCount; i++) {
            String[] line = scanner.nextLine().split(" ");
            int position = Integer.parseInt(line[0]);
            String direction = line[1];
            int moves = Integer.parseInt(line[2]);

            switch (direction){
                case "up":
                    swapUp(position, moves);
                    break;

                case "down":
                    swapDown(position, moves);
                    break;

                case "left":
                    swapLeft(position, moves);
                    break;

                case "right":
                    swapRight(position, moves);
                    break;
            }
        }

        int checker = 1;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == checker){
                    System.out.println("No swap required");
                } else {
                    int currentItem = matrix[row][col];
                    boolean swapped = false;
                    for (int r = 0; r < rows; r++) {
                        for (int c = 0; c < cols; c++) {
                            if (matrix[r][c] == checker){
                                matrix[row][col] = matrix[r][c];
                                matrix[r][c] = currentItem;
                                System.out.printf("Swap (%d, %d) with (%d, %d)\n", row, col, r, c);
                                swapped = true;
                                break;
                            }
                        }
                        if (swapped){
                            break;
                        }
                    }
                }
                checker++;
            }
        }
    }

    private static void swapRight(int position, int moves) {
        moves %= cols;
        for (int i = 0; i < moves; i++) {
            int temp = matrix[position][cols - 1];
            for (int c = cols - 1; c > 0; c--) {
                matrix[position][c] = matrix[position][c - 1];
            }
            matrix[position][0] = temp;
        }
    }

    private static void swapLeft(int position, int moves) {
        moves %= cols;
        for (int i = 0; i < moves; i++) {
            int temp = matrix[position][0];
            for (int c = 0; c < cols - 1; c++) {
                matrix[position][c]  = matrix[position][c + 1];
            }
            matrix[position][cols - 1] = temp;
        }
    }

    private static void swapDown(int position, int moves) {
        moves %= rows;
        for (int i = 0; i < moves; i++) {
            int temp = matrix[rows - 1][position];
            for (int r = rows - 1; r > 0; r--) {
                matrix[r][position] = matrix[r - 1][position];
            }
            matrix[0][position] = temp;
        }
    }

    private static void swapUp(int position, int moves) {
        moves %= rows;
        for (int i = 0; i < moves; i++) {
            int temp = matrix[0][position];
            for (int r = 0; r < rows - 1; r++) {
                matrix[r][position]  = matrix[r + 1][position];
            }
            matrix[rows - 1][position] = temp;
        }
    }
}
