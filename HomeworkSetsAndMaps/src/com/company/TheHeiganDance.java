package com.company;

import java.util.Scanner;

public class TheHeiganDance {
    private static int playerHitPoint = 18500;
    private static double heiganHitPoints = 3000000d;
    private static int currentRow = 7;
    private static int currentCol = 7;
    private static int maxRow = 15;
    private static int maxCol = 15;
    private static int plagueDamage = 3500;
    private static int eruptionDamage = 6000;
    private static String lastHitSpell = "Plague Cloud";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double damageDone = Double.parseDouble(scanner.nextLine());
        double damageToBeDone = 0;
        while (true){
            if (heiganHitPoints <= 0 || playerHitPoint <= 0){
                break;
            }
            String command = scanner.nextLine();
            String[] commandArgs = command.split("\\s+");
            String spell = commandArgs[0];
            int spellRow = Integer.parseInt(commandArgs[1]);
            int spellCol = Integer.parseInt(commandArgs[2]);
            heiganHitPoints -= damageDone;
            playerHitPoint -= damageToBeDone;
            damageToBeDone = 0;
            if (heiganHitPoints <= 0 || playerHitPoint <= 0){
                continue;
            }

            if (isPlayerInSpellRange(spellRow, spellCol, currentRow, currentCol)){
                boolean moved = false;
                if (isCellInMatrixRange(currentRow - 1, currentCol) && !isPlayerInSpellRange(spellRow, spellCol, currentRow - 1, currentCol)){
                    moved = true;
                    currentRow--;
                } else if (isCellInMatrixRange(currentRow, currentCol + 1) && !isPlayerInSpellRange(spellRow, spellCol, currentRow, currentCol + 1)){
                    moved = true;
                    currentCol++;
                } else if (isCellInMatrixRange(currentRow + 1, currentCol) && !isPlayerInSpellRange(spellRow, spellCol, currentRow + 1, currentCol)){
                    moved = true;
                    currentRow++;
                } else if (isCellInMatrixRange(currentRow, currentCol - 1) && !isPlayerInSpellRange(spellRow, spellCol, currentRow, currentCol - 1)){
                    moved = true;
                    currentCol--;
                }

                if (!moved){
                    switch (spell){
                        case "Cloud":
                            playerHitPoint -= plagueDamage;
                            damageToBeDone = plagueDamage;
                            lastHitSpell = "Plague Cloud";
                            break;
                        case "Eruption":
                            playerHitPoint -= eruptionDamage;
                            lastHitSpell = "Eruption";
                            break;
                    }
                }
            }
        }

        if (heiganHitPoints <= 0){
            System.out.println("Heigan: Defeated!");
        } else {
            System.out.printf("Heigan: %.2f%n", heiganHitPoints);
        }
        if (playerHitPoint <= 0){
            System.out.printf("Player: Killed by %s%n", lastHitSpell);
        } else {
            System.out.printf("Player: %d%n", playerHitPoint);
        }
        System.out.printf("Final position: %d, %d%n", currentRow, currentCol);
    }

    private static boolean isCellInMatrixRange(int row, int col) {
        return row >= 0 && row < maxRow && col >= 0 && col < maxCol;
    }

    private static boolean isPlayerInSpellRange(int spellRow, int spellCol, int playerRow, int playerCol) {
        for (int row = spellRow - 1; row <= spellRow + 1; row++) {
            for (int col = spellCol - 1; col <= spellCol + 1; col++) {
                if (row == playerRow && col == playerCol){
                    return true;
                }
            }
        }

        return false;
    }
}
