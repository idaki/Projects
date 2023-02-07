package Exercises;

import java.util.Arrays;
import java.util.Scanner;

public class PB03_Zig_Zag_Arrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int [] firstArray = new int[n];
        int [] secondArray = new int[n];

        for (int i = 1; i <= n; i++) {
        String[] input = scanner.nextLine().split(" ");
            if (i % 2!=0){
               firstArray[i-1] = Integer.parseInt(input[0]);
                secondArray[i-1] = Integer.parseInt(input[1]);
        }else {
                firstArray[i-1] = Integer.parseInt(input[1]);
                secondArray[i-1] = Integer.parseInt(input[0]);
            }
        }
        for (int element: firstArray) {
            System.out.print(element+" ");
        }
        System.out.println();
        for (int element: secondArray) {
            System.out.print(element+" ");
        }
    }
}
