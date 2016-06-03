package com.company;

import java.util.*;
import java.util.function.Predicate;

public class ThePartyReservationFilterModule {
    public static void main(String[] args) {
        Map<String, Predicate> filters = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        List<String> names = Arrays.asList(scanner.nextLine().split("\\s+"));
        while (true){
            String line = scanner.nextLine();
            if (line.equals("Print")){
                break;
            }

            String[] tokens = line.split(";");
            String command = tokens[0];
            String modifier = tokens[1];
            String variable = tokens[2];
            
            Predicate<String> predicate = buildPredicate(modifier, variable);

            switch (command){
                case "Add filter":
                    filters.put(modifier + variable, predicate);
                    break;
                case "Remove filter":
                    filters.remove(modifier + variable);
                    break;
            }
        }

        List<String> namesToInvite = new ArrayList<>();
        for (String name : names) {
            boolean toAdd = false;
            for (Predicate filter : filters.values()) {
                if (filter.test(name)){
                    toAdd = true;
                }
            }
            if (!toAdd){
                namesToInvite.add(name);
            }
        }

        namesToInvite.stream().forEach(n -> System.out.printf("%s ", n));
    }

    private static Predicate<String> buildPredicate(String modifier, String variable) {
        switch (modifier){
            case "Starts with":
                return x -> x.startsWith(variable);

            case "Ends with":
                return x -> x.endsWith(variable);

            case "Length":
                int length = Integer.parseInt(variable);
                return x -> x.length() == length;

            case "Contains":
                return x -> x.contains(variable);

            default:
                return null;
        }
    }
}
