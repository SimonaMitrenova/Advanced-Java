package com.company;

import java.util.Scanner;
import java.util.TreeSet;

public class PeriodicTable {
    public static void main(String[] args) {
        TreeSet<String> elements = new TreeSet<>();

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            for (String el : input) {
                elements.add(el);
            }
        }
        for (String element : elements) {
            System.out.print(element + " ");
        }
    }
}
