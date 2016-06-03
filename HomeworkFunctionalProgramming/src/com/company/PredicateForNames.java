package com.company;

import java.util.Scanner;
import java.util.function.Predicate;

public class PredicateForNames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maxLength = Integer.parseInt(scanner.nextLine());
        String[] names = scanner.nextLine().split("\\s+");
        filterNames(names, n -> n.length() <= maxLength);
    }
    private static void filterNames(String[] names, Predicate<String> predicate){
        for (String name : names) {
            if (predicate.test(name)){
                System.out.println(name);
            }
        }
    }
}
