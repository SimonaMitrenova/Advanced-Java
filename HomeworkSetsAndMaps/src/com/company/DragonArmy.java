package com.company;

import java.util.*;

public class DragonArmy {
    public static void main(String[] args) {
        int defaultHealth = 250;
        int defaultDamage = 45;
        int defaultArmor = 10;

        HashMap<String, double[]> dragonTypes = new HashMap<>();
        LinkedHashMap<String, TreeMap<String, int[]>> dragons = new LinkedHashMap<>();
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            String type = input[0];
            String dragon = input[1];
            int damage = input[2].equals("null") ? defaultDamage : Integer.parseInt(input[2]);
            int health = input[3].equals("null") ? defaultHealth : Integer.parseInt(input[3]);
            int armor = input[4].equals("null") ? defaultArmor : Integer.parseInt(input[4]);

            if (!dragons.containsKey(type)){
                dragons.put(type, new TreeMap<>());
                dragonTypes.put(type, new double[3]);
            }
            if (dragons.get(type).containsKey(dragon)){
                int[] stats = dragons.get(type).get(dragon);
                dragonTypes.get(type)[0] -= stats[0];
                dragonTypes.get(type)[1] -= stats[1];
                dragonTypes.get(type)[2] -= stats[2];
            }
            if (!dragons.get(type).containsKey(dragon)){
                dragons.get(type).put(dragon, new int[3]);
            }
            dragons.get(type).get(dragon)[0] = damage;
            dragons.get(type).get(dragon)[1] = health;
            dragons.get(type).get(dragon)[2] = armor;

            dragonTypes.get(type)[0] += damage;
            dragonTypes.get(type)[1] += health;
            dragonTypes.get(type)[2] += armor;
        }

        for (Map.Entry<String, TreeMap<String, int[]>> type : dragons.entrySet()) {
            System.out.printf("%s::(%.2f/%.2f/%.2f)%n",
                    type.getKey(),
                    dragonTypes.get(type.getKey())[0] / type.getValue().size(),
                    dragonTypes.get(type.getKey())[1] / type.getValue().size(),
                    dragonTypes.get(type.getKey())[2] / type.getValue().size());
            for (Map.Entry<String,int[]> dragon : type.getValue().entrySet()) {
                System.out.printf("-%s -> damage: %d, health: %d, armor: %d%n",
                        dragon.getKey(),
                        dragon.getValue()[0],
                        dragon.getValue()[1],
                        dragon.getValue()[2]);
            }
        }
    }
}
