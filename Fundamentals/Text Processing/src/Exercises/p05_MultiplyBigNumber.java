package Exercises;

import java.util.Scanner;

public class p05_MultiplyBigNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numAString = scanner.nextLine();
        int numB = Integer.parseInt(scanner.nextLine());

        int length = numAString.length();
        int reminder = 0;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i <= length; i++) {
            int lastDigit = 0;
            if (numAString.length() >= 1) {
                char lastChar = numAString.charAt(numAString.length() - 1);
                lastDigit = Integer.parseInt(String.valueOf(lastChar));
                numAString = numAString.substring(0, numAString.length() - 1);
            }
            int multiplyDigits = numB * lastDigit + reminder;
            reminder = 0;
            if (multiplyDigits > 9) {
                result.append(multiplyDigits % 10);
                reminder = (multiplyDigits - multiplyDigits % 10) / 10;
            } else {
                result.append(multiplyDigits);
            }
        }
      result.reverse();

        while (result.charAt(0) == '0'&& result.length()>1){
            result.deleteCharAt(0);
        }
        if (numB == 0) {
            System.out.println(0);
        } else {
            System.out.println(result);
        }

    }
}
