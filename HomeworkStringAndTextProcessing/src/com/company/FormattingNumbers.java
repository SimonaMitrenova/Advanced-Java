package com.company;

import java.math.BigInteger;
import java.util.Scanner;

public class FormattingNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().trim().split("\\s+");
        int a = Integer.parseInt(input[0]);
        double b = Double.parseDouble(input[1]);
        double c = Double.parseDouble(input[2]);
        String binary = Integer.toBinaryString(a).length() > 10 ? Integer.toBinaryString(a).substring(0, 10) : Integer.toBinaryString(a);
        System.out.printf("|%-10X|%010d|%10.2f|%-10.3f|", a, new BigInteger(binary), b, c);
    }
}
