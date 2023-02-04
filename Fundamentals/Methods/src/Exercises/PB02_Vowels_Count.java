package Exercises;

import java.util.Scanner;

public class PB02_Vowels_Count {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String textInput = scanner.nextLine();
        printVowelsCount(textInput);
    }

    public static void printVowelsCount(String text) {
        char[] textArray = text.toLowerCase().toCharArray();
        int counter = 0;
        for (int index = 0; index <= textArray.length - 1; index++) {
            if (textArray[index] == 'a' || textArray[index] == 'e' || textArray[index] == 'i'
                    || textArray[index] == 'o' || textArray[index] == 'u') {
                counter++;
            }
        }
        System.out.println(counter);
    }
}
