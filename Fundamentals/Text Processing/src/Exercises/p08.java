package Exercises;

import java.util.Scanner;

public class p08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] textArr = scanner.nextLine().split("\\s+");
        double sum = 0;

        for (String element : textArr) {
            StringBuilder tempElement = new StringBuilder(element);
            char firstLetter = tempElement.charAt(0);
            char lastLetter = tempElement.charAt(tempElement.length() - 1);
            tempElement.deleteCharAt(0);
            tempElement.deleteCharAt(tempElement.length() - 1);
            double num = Double.parseDouble(String.valueOf(tempElement));
            num = getFirstLetterNum(firstLetter, num);
            num = getLastLetterNum(lastLetter, num);
            sum += num;
        }

        System.out.printf("%.2f",sum);


    }

    private static Double getFirstLetterNum(char firstLetter, double num) {

        if (Character.isUpperCase(firstLetter)) {
            int position = firstLetter - 'A'+1;
            num /= position;
        } else {
            int position = firstLetter - 'a' + 1 ;
            num *= position;
        }
        return num;
    }

    private static Double getLastLetterNum(char lastLetter, double num) {

        if (Character.isUpperCase(lastLetter)) {
            int position = lastLetter - 'A'+1;
            num -= position;
        } else {
            int position = lastLetter - 'a'+1;
            num += position;
        }
        return num;
    }
}


