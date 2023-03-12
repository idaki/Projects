package Exercises;

import java.util.Scanner;

public class p01_ValidUsernames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] usernameArr = scanner.nextLine().split(", ");

        for (String element: usernameArr) {
            if (isLengthValid(element) && isSymbolValid(element)){
                System.out.println(element);
            }
        }
    }

    private static boolean isLengthValid(String element) {
        boolean isLengthValid = false;
            int elementLength = element.length();
            if (elementLength>= 3 && elementLength <= 16) {
                isLengthValid = true;
            }
        return isLengthValid;
    }

    private static boolean isSymbolValid (String element) {
        boolean isSymbolValid = false;
        char[] symbolArr = element.toCharArray();
        for (char symbol : symbolArr) {
          if(Character.isAlphabetic(symbol) || Character.isDigit(symbol)|| symbol =='-' || symbol =='_' ) {
              isSymbolValid = true;
          }else{
              isSymbolValid = false;
              break;
          }
        }
        return isSymbolValid;
    }
}
