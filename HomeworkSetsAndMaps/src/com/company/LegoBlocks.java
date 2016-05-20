package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class LegoBlocks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> firstMatrix = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] numbers = scanner.nextLine().trim().split("\\s+");
            firstMatrix.add(new ArrayList<>());
            for (String number : numbers) {
                firstMatrix.get(i).add(Integer.parseInt(number));
            }
        }
        List<List<Integer>> secondMatrix = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] numbers = scanner.nextLine().trim().split("\\s+");
            secondMatrix.add(new ArrayList<>());
            for (int j = numbers.length - 1; j >= 0; j--) {
                secondMatrix.get(i).add(Integer.parseInt(numbers[j]));
            }
        }

        int length = firstMatrix.get(0).size() + secondMatrix.get(0).size();
        boolean sameLength = true;
        int count = length;
        for (int i = 1; i < n; i++) {
            if (length != firstMatrix.get(i).size() + secondMatrix.get(i).size()){
                sameLength = false;
            }
            count += firstMatrix.get(i).size() + secondMatrix.get(i).size();
        }
        if (sameLength){
            for (int i = 0; i < n; i++) {
                System.out.printf("[%s, %s]%n",
                        firstMatrix.get(i).stream().map(Object::toString).collect(Collectors.joining(", ")),
                        secondMatrix.get(i).stream().map(Object::toString).collect(Collectors.joining(", ")));
            }
        } else {
            System.out.printf("The total number of cells is: %d%n", count);
        }
    }
}
