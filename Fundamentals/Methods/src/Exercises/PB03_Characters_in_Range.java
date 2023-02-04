package Exercises;

import java.util.Scanner;

public class PB03_Characters_in_Range {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char firstCharInput = scanner.nextLine().charAt(0);
        char secondCharInput = scanner.nextLine().charAt(0);

        printCharactersInRange(firstCharInput, secondCharInput);
    }

    public static void printCharactersInRange(char start, char end) {
        if (start < end) {
            for (int i = start + 1; i < end; i++) {
                System.out.print((char) i + " ");
            }
        } else{
            for (int i = end+1; i < start ; i++) {
                System.out.print((char) i + " ");
            }
        }

    }

}
