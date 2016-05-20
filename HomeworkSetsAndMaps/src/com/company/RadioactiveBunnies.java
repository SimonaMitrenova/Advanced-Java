package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class RadioactiveBunnies {
    private static char[][] lair;
    private static int rows;
    private static int cols;
    private static int playerRow = 0;
    private static int playerCol = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        rows = dimensions[0];
        cols = dimensions[1];
        lair = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            lair[row] = scanner.nextLine().toCharArray();
        }

        String commands = scanner.nextLine();
        findPlayer();

        boolean isDead = false;
        boolean hasWon = false;
        for (int index = 0; index < commands.length(); index++) {
            if (isDead || hasWon){
                break;
            }
            switch (commands.charAt(index)){
                case 'U':
                    playerRow--;
                    hasWon = isOutOfRange(playerRow, playerCol);
                    if (hasWon){
                        playerRow++;
                    } else {
                        isDead = hasBunnyOnCell(playerRow, playerCol);
                    }
                    break;

                case 'R':
                    playerCol++;
                    hasWon = isOutOfRange(playerRow, playerCol);
                    if (hasWon){
                        playerCol--;
                    } else {
                        isDead = hasBunnyOnCell(playerRow, playerCol);
                    }
                    break;

                case 'D':
                    playerRow++;
                    hasWon = isOutOfRange(playerRow, playerCol);
                    if (hasWon){
                        playerRow--;
                    } else {
                        isDead = hasBunnyOnCell(playerRow, playerCol);
                    }
                    break;

                case 'L':
                    playerCol--;
                    hasWon = isOutOfRange(playerRow, playerCol);
                    if (hasWon){
                        playerCol++;
                    } else {
                        isDead = hasBunnyOnCell(playerRow, playerCol);
                    }
                    break;
            }

            populateBunnies();
            isDead = hasBunnyOnCell(playerRow, playerCol);
        }

        printLair();
        if (hasWon){
            System.out.printf("won: %d %d\n", playerRow, playerCol);
        } else {
            System.out.printf("dead: %d %d\n", playerRow, playerCol);
        }
    }

    private static void printLair() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print(lair[row][col]);
            }
            System.out.println();
        }
    }

    private static void populateBunnies() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (lair[row][col] == 'B'){
                    if (!isOutOfRange(row - 1, col) && !hasBunnyOnCell(row - 1, col)){
                        lair[row -1][col] = 'b';
                    }
                    if (!isOutOfRange(row, col + 1) && !hasBunnyOnCell(row, col + 1)){
                        lair[row][col +1] = 'b';
                    }
                    if (!isOutOfRange(row + 1, col) && !hasBunnyOnCell(row + 1, col)){
                        lair[row + 1][col] = 'b';
                    }
                    if (!isOutOfRange(row, col -1) && !hasBunnyOnCell(row, col - 1)){
                        lair[row][col -1] = 'b';
                    }
                }
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (lair[row][col] == 'b'){
                    lair[row][col] = 'B';
                }
            }
        }
    }

    private static boolean hasBunnyOnCell(int row, int col) {
        if (lair[row][col] == 'B'){
            return true;
        }
        return false;
    }

    private static boolean isOutOfRange(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols){
            return true;
        }
        return false;
    }

    private static void findPlayer() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (lair[row][col] == 'P'){
                    playerRow = row;
                    playerCol = col;
                    lair[row][col] = '.';
                }
            }
        }
    }
}
