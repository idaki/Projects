package Exercises;

import java.util.Scanner;

public class PB09_Palindrome_Integers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine = "";
        while (!"END".equals(inputLine = scanner.nextLine())) {

            char[] inputNumberCharArr = inputLine.toCharArray();
            System.out.println(isPalindromeInteger(inputNumberCharArr));
        }
    }

    public static boolean isPalindromeInteger(char[] numCharArray) {
        boolean isPalindromeInteger = true;
        if (numCharArray.length % 2 != 0) {

            for (int j = numCharArray.length - 1; j > numCharArray.length / 2; j--) {
                if (numCharArray[numCharArray.length - 1 - j] != numCharArray[j]) {
                    isPalindromeInteger = false;
                    break;
                }
            }
        } else {

            for (int j = numCharArray.length - 1; j >= numCharArray.length / 2; j--) {
                if (numCharArray[numCharArray.length - 1 - j] != numCharArray[j]) {
                    isPalindromeInteger = false;
                    break;
                }
            }
        }

        return isPalindromeInteger;
    }

}
