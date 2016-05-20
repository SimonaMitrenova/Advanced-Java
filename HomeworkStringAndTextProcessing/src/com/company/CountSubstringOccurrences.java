package com.company;

import java.util.Scanner;

public class CountSubstringOccurrences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine().toLowerCase();
        String pattern = scanner.nextLine().toLowerCase();

        int count = 0;
        int index = text.indexOf(pattern);
        while (index != -1){
            count++;
            index = text.indexOf(pattern, index + 1);
        }
        System.out.println(count);
    }
}
