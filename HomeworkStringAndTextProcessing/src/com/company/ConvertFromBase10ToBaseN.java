package com.company;

import java.math.BigInteger;
import java.util.Scanner;

public class ConvertFromBase10ToBaseN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        int base = Integer.parseInt(input[0]);
        BigInteger number = new BigInteger(input[1]);
        System.out.println(number.toString(base));
    }
}
