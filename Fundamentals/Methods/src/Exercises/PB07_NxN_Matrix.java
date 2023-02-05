package Exercises;

import java.util.Scanner;

public class PB07_NxN_Matrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());
        printSquare(number);
    }
    public static void printSquare(int num){
        for (int i = 0; i < num ; i++) {
            for (int j = 0; j < num; j++) {
                System.out.print(num+" ");
            }
            System.out.println();
        }
    }
}
