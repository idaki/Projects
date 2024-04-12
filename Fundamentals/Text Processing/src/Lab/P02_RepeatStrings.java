package Lab;

import java.util.Arrays;
import java.util.Scanner;

public class P02_RepeatStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] textArr = scanner.nextLine().split(" ");
        for (int i = 0; i <= textArr.length - 1 ; i++)
        {
            textArr[i] = getElementEdited( textArr[i]);
        }
        System.out.println(Arrays.toString(textArr).replaceAll("[\\[\\], ]",""));
    }

    private static String getElementEdited(String element) {
        String result ="";
        int n = element.length();
        for (int i = 0; i < n; i++) {
            result += element;
        }
        return result;
    }
}
