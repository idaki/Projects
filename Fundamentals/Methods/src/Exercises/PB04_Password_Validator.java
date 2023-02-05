package Exercises;

import java.util.Scanner;

public class PB04_Password_Validator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] textArray = scanner.nextLine().toLowerCase().toCharArray();

        if (confirmLength(textArray)
                && confirmDigAndLetters(textArray)
                && confirmDigitNumberCount(textArray)) {
            System.out.println("Password is valid");
        } else {
            if (!confirmLength(textArray)) {
                System.out.println("Password must be between 6 and 10 characters");
            }
            if (!confirmDigAndLetters(textArray)) {
                System.out.println("Password must consist only of letters and digits");
            }
            if (!confirmDigitNumberCount(textArray)) {
                System.out.println("Password must have at least 2 digits");
            }
        }
    }

    public static boolean confirmLength(char[] text) {
        boolean isLengthConfirmed = false;
        if (text.length >= 6 && text.length <= 10) {
            isLengthConfirmed = true;
        }
        return isLengthConfirmed;
    }

    public static boolean confirmDigAndLetters(char[] text) {
        boolean isStructureConfirmed = true;
        for (int index = 0; index <= text.length - 1; index++) {
            if (!(text[index] >= 'a' && text[index] <= 'z') && !((text[index] >= '0') && text[index] <= '9')) {
                isStructureConfirmed = false;
                break;
            }
        }
        return isStructureConfirmed;
    }

    public static boolean confirmDigitNumberCount(char[] text) {
        boolean isDigNumberConfirmed = false;
        int counterDigits = 0;
        for (int index = 0; index <= text.length - 1; index++) {
            if (text[index] >= '0' && text[index] <= '9') {
                counterDigits++;
                if (counterDigits == 2) {
                    isDigNumberConfirmed = true;
                    break;
                }
            }
        }
        return isDigNumberConfirmed;
    }
}
