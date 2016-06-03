package com.company;

import java.util.*;

public class CustomComparator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        scan = new Scanner(line);

        List<Integer> numbers = new ArrayList<>();

        while (scan.hasNextInt()) {
            Integer n = scan.nextInt();
            numbers.add(n);
        }

        Integer[] result = new Integer[numbers.size()];
        numbers.toArray(result);

        Arrays.sort(result, customIntegerComparator);
        Arrays.stream(result).forEach(e -> System.out.printf("%d ", e));
    }
    private static Comparator<Integer> customIntegerComparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer first, Integer second) {
            if (first % 2 == 0 && second % 2 != 0){
                return -1;
            } else if (first % 2 != 0 && second % 2 == 0){
                return 1;
            } else {
                return first - second;
            }
        }
    };
}
