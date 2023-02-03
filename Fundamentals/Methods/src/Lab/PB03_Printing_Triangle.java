package Lab;

import java.util.Scanner;

public class PB03_Printing_Triangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int endNumberInput = Integer.parseInt(scanner.nextLine());
        printGrowingSideOfTriangle(endNumberInput);
        printDecliningSideOfTriangle(endNumberInput);
    }

    public static void printGrowingSideOfTriangle(int endNumber){
        for (int i = 0; i < endNumber; i++) {
            for (int j = 1; j <=i + 1 ; j++) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }
    
    public static void printDecliningSideOfTriangle(int endNumber){
        for (int i = endNumber; i >0 ; i--) {
            for (int j = 1; j < i ; j++) {
                System.out.print(j + " ");
            }
            System.out.println("");
        }
    }
}
