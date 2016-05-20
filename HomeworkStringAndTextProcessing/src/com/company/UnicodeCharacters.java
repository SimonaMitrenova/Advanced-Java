package com.company;

        import java.util.Scanner;

public class UnicodeCharacters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder result = new StringBuilder();
        String text = scanner.nextLine();
        for (int i = 0; i < text.length(); i++) {
            result.append(String.format("\\u00%x", (int)text.charAt(i)));
        }
        System.out.println(result);
    }
}
