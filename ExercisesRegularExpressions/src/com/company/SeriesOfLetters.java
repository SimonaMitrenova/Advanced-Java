package com.company;

import java.util.Scanner;

public class SeriesOfLetters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String replaced = input.replaceAll("(.)\\1+", "$1");
        System.out.println(replaced);
    }
}
