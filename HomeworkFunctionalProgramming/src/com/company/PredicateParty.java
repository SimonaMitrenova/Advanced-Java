package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class PredicateParty {
    private static List<String> names;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        names = Arrays.asList(scanner.nextLine().split("\\s+"));

        while (true){
            String line = scanner.nextLine();
            if (line.equals("Party!")){
                break;
            }
            String[] tokens = line.split("\\s+");
            String command = tokens[0];
            String modifier = tokens[1];
            String variable = tokens[2];

            Predicate<String> predicate = buildPredicate(modifier, variable);

            switch (command){
                case "Remove":
                    removeIf(predicate);
                    break;
                case "Double":
                    doubleIf(predicate);
                    break;
            }
        }
        if (names.size() == 0){
            System.out.println("Nobody is going to the party!");

        } else {
            System.out.printf("%s are going to the party!%n", String.join(", ", names));
        }
    }

    private static void doubleIf(Predicate<String> predicate) {
        List<String> newNames = new ArrayList<>();
        for (String name : names) {
            if (predicate.test(name)){
                newNames.add(name);
            }
            newNames.add(name);
        }

        names = newNames;
    }

    private static void removeIf(Predicate<String> predicate) {
        List<String> newNames = new ArrayList<>();
        for (String name : names) {
            if (!predicate.test(name)){
                newNames.add(name);
            }
        }

        names = newNames;
    }

    private static Predicate<String> buildPredicate(String modifier, String variable){
        switch (modifier){
            case "StartsWith":
                return x -> x.startsWith(variable);

            case "EndsWith":
                return x -> x.endsWith(variable);

            case "Length":
                int length = Integer.parseInt(variable);
                return x -> x.length() == length;

            default:
                return null;
        }
    }
}
