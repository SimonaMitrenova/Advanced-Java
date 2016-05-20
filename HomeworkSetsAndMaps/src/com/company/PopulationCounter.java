package com.company;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class PopulationCounter {
    public static void main(String[] args) {
        LinkedHashMap<String, LinkedHashMap<String, Long>> citiesByCountry = new LinkedHashMap<>();
        LinkedHashMap<String, Long> countriesByPopulation = new LinkedHashMap<>();

        Scanner scanner = new Scanner(System.in);

        while (true){
            String input = scanner.nextLine();
            if ("report".equals(input)){
                break;
            }
            String[] inputParams = input.split("\\|");
            String city = inputParams[0].trim();
            String country = inputParams[1].trim();
            Long population = Long.parseLong(inputParams[2].trim());
            if (!countriesByPopulation.containsKey(country)){
                countriesByPopulation.put(country, 0L);
                citiesByCountry.put(country, new LinkedHashMap<>());
            }
            if (!citiesByCountry.get(country).containsKey(city)){
                citiesByCountry.get(country).put(city, 0L);
            }
            countriesByPopulation.put(country, countriesByPopulation.get(country) + population);
            citiesByCountry.get(country).put(city, citiesByCountry.get(country).get(city) + population);
        }

        countriesByPopulation.entrySet().stream().sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())).forEach(country -> {
            System.out.printf("%s (total population: %d)\n", country.getKey(), country.getValue());
            citiesByCountry.get(country.getKey()).entrySet().stream().sorted((c1, c2) -> c2.getValue().compareTo(c1.getValue())).forEach(city -> {
                System.out.printf("=>%s: %d\n", city.getKey(), city.getValue());
            });
        });
    }
}
