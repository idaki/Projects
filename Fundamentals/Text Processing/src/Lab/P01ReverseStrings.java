package Lab;

import java.util.Scanner;

public class P01ReverseStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = "";

        while(!"end".equals( text = scanner.nextLine())){
            String reversedText = "";
            char[] textArr = text.toCharArray();
            for (int i = text.length()-1; i >=0 ; i--) {
               char symbol = textArr[i];
               reversedText += symbol;
            }
            System.out.println(text+" = "+ reversedText);

        }


    }
}
