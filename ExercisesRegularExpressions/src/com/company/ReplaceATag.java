package com.company;

import java.util.Scanner;

public class ReplaceATag {
    public static void main(String[] args) {
        StringBuilder results = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine();
            if (input.equals("end")){
                break;
            }
            input = input.replaceAll("<a", "[URL");
            input = input.replaceAll("</a>", "[/URL]");
            results.append(input);
        }
        System.out.print(results);
    }
}
