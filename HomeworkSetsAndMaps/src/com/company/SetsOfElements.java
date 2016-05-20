package com.company;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class SetsOfElements {
    public static void main(String[] args) {
        Set<Integer> first = new LinkedHashSet<>();
        Set<Integer> second = new LinkedHashSet<>();

        Scanner scanner = new Scanner(System.in);
        String[] dimensions = scanner.nextLine().split("\\s+");
        int n = Integer.parseInt(dimensions[0]);
        int m = Integer.parseInt(dimensions[1]);
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(scanner.nextLine());
            first.add(num);
        }
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(scanner.nextLine());
            second.add(num);
        }
        first.retainAll(second);
        for (Integer integer : first) {
            System.out.printf("%d ", integer);
        }
    }
}
