package Exercises;

import java.util.Scanner;

public class p07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int explosionPoints = 0;
        StringBuilder print = new StringBuilder(input);
        for (int i = 0; i < print.length(); i++) {
            char currentSymbol = print.charAt(i);
            if (currentSymbol =='>'){
                 explosionPoints += Integer.parseInt(String.valueOf(print.charAt(i+1)));
            }else {
                if (explosionPoints>0){
                    print.deleteCharAt(i);
                    explosionPoints--;
                    i--;
                }
            }
        }
        System.out.println(print);
    }
}
