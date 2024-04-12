package Exercises;

import java.util.Scanner;

public class p02Character_Multiplier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputArr = scanner.nextLine().split(" ");
        String str1 = inputArr[0];
        String str2 = inputArr[1];
        System.out.println(result(str1, str2));
    }

    public static int result(String str1, String str2) {
        int result = 0;

        int minLength = Math.min(str1.length(), str2.length());
        int maxLength = Math.max(str1.length(),str2.length());
        int sum =0;
        for (int i = 0; i < minLength; i++) {
            sum = str1.charAt(i)* str2.charAt(i);
            result += sum;
        }


        if (minLength == str1.length()) {
            for (int i = minLength; i < maxLength; i++) {
                result += str2.charAt(i);
            }

        } else if (minLength == str2.length()) {
            for (int i = minLength; i < maxLength; i++) {
                result += str1.charAt(i);
            }
        }
        return result;
    }

}
