package Lab;

import java.util.Random;
import java.util.Scanner;

public class PB01_Randomize_Words {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputTxt = scanner.nextLine().split(" ");

        Random rnd = new Random();


        for (int i = 0; i <= inputTxt.length-1 ; i++) {
            int randomNum = rnd.nextInt(inputTxt.length);
            String temp = inputTxt[randomNum];
            inputTxt[randomNum] = inputTxt[i];
            inputTxt[i] = temp;
        }

        for (String element: inputTxt) {
            System.out.println(element);

        }

    }
}
