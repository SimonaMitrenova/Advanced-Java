package com.company;

import java.util.Scanner;

public class StringLength {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder word = new StringBuilder(scanner.nextLine());
        if (word.length() >= 20){
            System.out.println(word.replace(20, word.length(), ""));
        }else {

            for (int i = word.length(); i < 20; i++) {
                word.append('*');
            }

            System.out.println(word);
        }
    }
}
