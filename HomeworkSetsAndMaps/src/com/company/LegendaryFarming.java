package com.company;

import java.util.*;

public class LegendaryFarming {
    public static void main(String[] args) {
        HashMap<String, Integer> materials = new HashMap<>();
        materials.put("fragments", 0);
        materials.put("shards", 0);
        materials.put("motes", 0);
        TreeMap<String, Integer> junk = new TreeMap<>();

        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;
        String winMaterial = "";
        while (true){
            if (!shouldContinue){
                break;
            }

            String[] arguments = scanner.nextLine().toLowerCase().split("\\s+");
            for (int i = 1; i < arguments.length; i += 2) {
                if (materials.containsKey(arguments[i])){
                    materials.put(arguments[i], materials.get(arguments[i]) + Integer.parseInt(arguments[i -1]));
                    if (materials.get(arguments[i]) >= 250){
                        winMaterial = arguments[i];
                        materials.put(arguments[i], materials.get(arguments[i]) - 250);
                        shouldContinue = false;
                        break;
                    }
                } else {
                    if (!junk.containsKey(arguments[i])){
                        junk.put(arguments[i], 0);
                    }

                    junk.put(arguments[i], junk.get(arguments[i]) + Integer.parseInt(arguments[i -1]));
                }
            }
        }

        switch (winMaterial){
            case "fragments":
                System.out.println("Valanyr obtained!");
                break;
            case "shards":
                System.out.println("Shadowmourne obtained!");
                break;
            case "motes":
                System.out.println("Dragonwrath obtained!");
                break;
        }

        LinkedHashMap<String, Integer> ordered = new LinkedHashMap<>();
        materials.entrySet().stream().sorted((e1, e2) -> {
            int result = e2.getValue().compareTo(e1.getValue());
            if (result == 0){
                result = e1.getKey().compareTo(e2.getKey());
            }
            return result;
        }).forEach(e -> ordered.put(e.getKey(), e.getValue()));

        System.out.printf("");
        for (Map.Entry<String, Integer> pair : ordered.entrySet()) {
            System.out.printf("%s: %d\n", pair.getKey(), pair.getValue());
        }

        for (Map.Entry<String, Integer> pair : junk.entrySet()) {
            System.out.printf("%s: %d\n", pair.getKey(), pair.getValue());
        }
    }
}
