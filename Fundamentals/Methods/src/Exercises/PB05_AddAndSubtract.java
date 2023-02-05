package Exercises;

import java.util.Scanner;

public class PB05_AddAndSubtract {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numA = Integer.parseInt(scanner.nextLine());
        int numB = Integer.parseInt(scanner.nextLine());
        int numC = Integer.parseInt(scanner.nextLine());
        int result = getSum(numA,numB) - numC;
        System.out.println(result);
    }

    public static int getSum(int a, int b){
        return a+b;
    }


}
