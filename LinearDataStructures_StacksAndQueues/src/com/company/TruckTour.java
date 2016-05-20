package com.company;

import java.math.BigDecimal;
import java.util.Scanner;

public class TruckTour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);        
        int n = Integer.parseInt(scanner.nextLine());
        BigDecimal[] petrol = new BigDecimal[n];
        BigDecimal[] distance = new BigDecimal[n];
        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            petrol[i] = new BigDecimal(input[0]);
            distance[i] = new BigDecimal(input[1]);
        }

        for (int i = 0; i < n; i++) {
            boolean pathFound = true;
            BigDecimal petrolCarrying = new BigDecimal(0);
            for (int j = 0; j < n; j++) {
                int currentPomp = (i + j) % n;
                petrolCarrying = petrolCarrying.add(petrol[currentPomp]);
                petrolCarrying = petrolCarrying.subtract(distance[currentPomp]);
                if (petrolCarrying.compareTo(BigDecimal.ZERO) < 0){
                    pathFound = false;
                    break;
                }
            }
            if (pathFound){
                System.out.println(i);
                return;
            }
        }
    }
}
