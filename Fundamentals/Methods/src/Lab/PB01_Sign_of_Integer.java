package Lab;

import java.util.Scanner;

public class PB01_Sign_of_Integer {
    public static void main(String[] args) {
        printSignOfInteger ();
    }

    public static void printSignOfInteger(){
        Scanner scanner = new Scanner(System.in);
        int integerInput = Integer.parseInt(scanner.nextLine());

        if (integerInput>0){
            System.out.println("The number "+ integerInput + " is positive.");
        } else if (integerInput==0) {
            System.out.println("The number 0 is zero.");
        }else{
            System.out.println("The number " + integerInput +" is negative.");
        }

    }
}
