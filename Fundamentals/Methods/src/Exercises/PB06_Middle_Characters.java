package Exercises;

import java.util.Scanner;

public class PB06_Middle_Characters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] textInput = scanner.nextLine().toCharArray();

        System.out.println(getMiddleElements(textInput));
    }

    public static String getMiddleElements(char[] text) {
        String output = "";
        int indexElement = text.length / 2;
        if (text.length % 2 != 0) {
            output = String.format("%s", text[indexElement]);
        } else {
            output = String.format("%s%s"
                    , text[indexElement - 1]
                    , text[indexElement]);
        }

        return output;
    }
}
