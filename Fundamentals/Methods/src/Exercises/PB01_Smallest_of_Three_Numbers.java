package Exercises;

import java.util.Scanner;

public class PB01_Smallest_of_Three_Numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = Integer.parseInt(scanner.nextLine());
        int b = Integer.parseInt(scanner.nextLine());
        int c = Integer.parseInt(scanner.nextLine());
        printSmallestInteger(a,b,c);
    }
    public static void printSmallestInteger(int firstNum, int secondNum, int thirdNum){
        int output = Math.min(Math.min(firstNum,secondNum),thirdNum);
        System.out.println(output);
    }
}
