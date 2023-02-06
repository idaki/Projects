package Lab;

import java.util.Scanner;

public class PB02_Print_Numbers_in_Reverse_Order {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = Integer.parseInt(scanner.nextLine());
        int [] inputArray = new int[a];
        for (int i = 0; i < a ; i++) {
            inputArray[i] = Integer.parseInt(scanner.nextLine());
        }
        for (int i = a-1; i >=0 ; i--) {
            System.out.println(inputArray[i]+" ");
        }


    }
}
