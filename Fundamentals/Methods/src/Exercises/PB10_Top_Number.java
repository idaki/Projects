package Exercises;

import java.util.Scanner;

public class PB10_Top_Number {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberInput = Integer.parseInt(scanner.nextLine());
        numberInput = Math.abs(numberInput);
        for (int i = 1; i < numberInput ; i++) {
            if (isDivisibleByEight(i) && isHoldingOddInteger(i)) {
                System.out.println(i);
            }
        }
    }

    public static boolean isDivisibleByEight(int num) {
        boolean isDivisible = true;
        int sum = 0, lastDigit = 0;
        while (num > 0) {
            if (num % 10 != 0) {
                lastDigit = num % 10;
                sum += lastDigit;
            }
            num /= 10;
        }
        if (sum % 8 != 0) {
            isDivisible = false;
        }
        return isDivisible;
    }

    public static boolean isHoldingOddInteger(int num) {
        boolean isOdd = false;

        while (num > 0) {
            int lastDigit = num % 10;
           if (lastDigit != 0) {
               if (lastDigit % 2 != 0) {
                   isOdd = true;
                   break;
               }
           }
            num /= 10;

        }
        return isOdd;
    }
}

