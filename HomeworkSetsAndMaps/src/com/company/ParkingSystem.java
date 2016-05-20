package com.company;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;

public class ParkingSystem {
    private static int rows;
    private static int cols;

    public static void main(String[] args) {
        TreeMap<Integer, HashSet<Integer>> parkingMatrix = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        rows = dimensions[0];
        cols = dimensions[1];
        for (int row = 0; row < rows; row++) {
            parkingMatrix.put(row, new HashSet<>());
        }

        while (true){
            String input = scanner.nextLine();
            if ("stop".equals(input)){
                break;
            }
            int[] carArgs = Arrays.stream(input.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int entryRow = carArgs[0];
            int rowToPark = carArgs[1];
            int colToPark = carArgs[2];
            boolean hasParked = false;
            if (!parkingMatrix.get(rowToPark).contains(colToPark)){
                parkingMatrix.get(rowToPark).add(colToPark);
                int distance = Math.abs(entryRow - rowToPark) + colToPark + 1;
                hasParked = true;
                System.out.println(distance);
                continue;
            }

            int colToParkLeft = colToPark - 1;
            int colToParkRight = colToPark + 1;
            boolean isInRangeLeft = isInRange(colToParkLeft);
            boolean isInRangeRight = isInRange(colToParkRight);
            while (isInRangeLeft || isInRangeRight){
                if (isInRangeLeft && !parkingMatrix.get(rowToPark).contains(colToParkLeft)){
                    parkingMatrix.get(rowToPark).add(colToParkLeft);
                    int distance = Math.abs(entryRow - rowToPark) + colToParkLeft + 1;
                    hasParked = true;
                    System.out.println(distance);
                    break;
                } else {
                    colToParkLeft--;
                    isInRangeLeft = isInRange(colToParkLeft);
                }

                if (isInRangeRight && !parkingMatrix.get(rowToPark).contains(colToParkRight)){
                    parkingMatrix.get(rowToPark).add(colToParkRight);
                    int distance = Math.abs(entryRow - rowToPark) + colToParkRight + 1;
                    hasParked = true;
                    System.out.println(distance);
                    break;
                } else {
                    colToParkRight++;
                    isInRangeRight = isInRange(colToParkRight);
                }
            }

            if (!hasParked){
                System.out.printf("Row %d full\n", rowToPark);
            }
        }
    }

    private static boolean isInRange(int col) {
        return (col > 0) && (col < cols);
    }
}
