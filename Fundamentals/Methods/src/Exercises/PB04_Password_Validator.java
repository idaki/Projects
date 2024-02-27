package Exercises;

import java.util.Arrays;
import java.util.Scanner;

public class PB04_Password_Validator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();

        char[] passwordArray = password.toLowerCase().toCharArray();





        if (confirmPasswordLength(passwordArray)
                && confirmCharacterCombination(passwordArray)
                &&  confirmDigitNumber(passwordArray) )  {
            System.out.println("password is ok" );
        }   else{

        }


    }

    public static boolean confirmPasswordLength(char[] password) {
        if (password.length >= 6 && password.length <= 10) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean confirmCharacterCombination(char[] password) {
        for (int index = 0; index <= password.length - 1; index++) {

            if (!(password[index] > (char) 0 && (password[index] <= (char) 9) ||
                    !(password[index] >= 'a' && password[index] <= 'z'))) {

            }  else {

            }
        }
        return true;   
    }

    public static boolean confirmDigitNumber(char[] password) {
     int counter = 0;
      for (int index = 0; index <= password.length - 1; index++) {
              if (password[index] > (char)0 && password[index] <= (char) 9){
                 counter++;
                 if (counter>=2){
                     return true;
                 } else {
                      return false;
                 }
              }
        }
        return false;
    }
}
