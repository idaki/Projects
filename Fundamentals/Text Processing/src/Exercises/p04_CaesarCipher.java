package Exercises;

import java.util.Scanner;

public class p04_CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String encryptedText = "";
        for (int i = 0; i < text.length(); i++) {
        char encryptedChar = (char)(text.charAt(i)+3);
        encryptedText += encryptedChar ;
        }
        System.out.println(encryptedText);
    }
}
