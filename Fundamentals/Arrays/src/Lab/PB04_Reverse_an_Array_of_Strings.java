package Lab;

import java.util.Scanner;

public class PB04_Reverse_an_Array_of_Strings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] text = scanner.nextLine().split(" ");

        // 0 1 2 3 4

        for (int index = 0; index <= text.length / 2; index++) {
            String temp = "";
            temp = text[index];
            text [index] = text[text.length-1-index];
            text[text.length-1-index] = temp;
        }
        for (String element:text) {
            System.out.print(element+" ");
        }
    }
}
