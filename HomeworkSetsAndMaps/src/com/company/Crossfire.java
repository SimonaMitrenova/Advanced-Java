package com.company;

import java.util.*;

public class Crossfire {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];
        List<List<Integer>> matrix = new ArrayList<>();
        int counter = 1;
        for (int row = 0; row < rows; row++) {
            matrix.add(new ArrayList<>());
            for (int col = 0; col < cols; col++) {
                matrix.get(row).add(counter);
                counter++;
            }
        }

        while (true){
            String input = scanner.nextLine();
            if ("Nuke it from orbit".equals(input)){
                break;
            }
            int[] arguments = Arrays.stream(input.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int bombRow = arguments[0];
            int bombCol = arguments[1];
            int bombRadius = arguments[2];

            for (int currentRow = bombRow - bombRadius; currentRow <= bombRow + bombRadius; currentRow++) {
                if (isInMatrix(currentRow, bombCol, matrix)){
                    matrix.get(currentRow).set(bombCol, -1);
                }                
            }

            for (int currentCol = bombCol - bombRadius; currentCol <= bombCol + bombRadius; currentCol++) {
                if (isInMatrix(bombRow, currentCol, matrix)){
                    matrix.get(bombRow).set(currentCol, -1);
                }
            }

            filterMatrix(matrix);
        }

        for (int row = 0; row < matrix.size(); row++) {
            matrix.get(row).stream().forEach(e -> System.out.printf("%d ", e));
            System.out.println();
        }
    }

    private static void filterMatrix(List<List<Integer>> matrix){
        for (int row = 0; row < matrix.size(); row++) {
            matrix.get(row).removeAll(Arrays.asList((new Integer[] {-1})));
        }
        matrix.removeAll(Arrays.asList(new ArrayList<Integer>()));
    }

    private static boolean isInMatrix(int currentRow, int currentCol, List<List<Integer>> matrix) {
        return currentRow >= 0 && currentRow < matrix.size() && currentCol >= 0 && currentCol < matrix.get(currentRow).size();
    }
}