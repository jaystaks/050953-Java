package com.company;
import java.util.Scanner;

public class MultiplicationTable {
    private static Scanner in;
    public static void main(String[] args) {
        int number;
        in = new Scanner(System.in);

        System.out.println("Enter a number to get it's multiplication table");
        number = in.nextInt();

        printTable(number);
    }

    public static void printTable(int num){

        System.out.println("Enter the range");
        int r = in.nextInt();

        for(int i = num; i < r; i++) {
            for (int j = 1; j <= r; j++) {
                System.out.println(i + "  *  " + j + "  =  " + i * j);
            }
            System.out.println("----------------");
        }
    }

}
