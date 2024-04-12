package Exercises;

import java.util.Scanner;

public class p06_ReplaceRepeatingChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        StringBuilder print = new StringBuilder();
        char[] textArr = input.toCharArray();
        print.append(textArr[0]);
        for (int i = 0; i < input.length()-1; i++) {
            if (textArr[i]!=textArr[i+1] ){
                print.append(textArr[i+1]);
            }
        }
        System.out.println(print);

    }
        }
