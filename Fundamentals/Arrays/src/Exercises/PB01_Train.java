package Exercises;

import java.util.Scanner;

public class PB01_Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] arrayInput = new int[n];
        int sum = 0;

        for (int i = 0; i < n ; i++) {
            arrayInput [i] = Integer.parseInt(scanner.nextLine());
            sum += arrayInput [i];
        }

        for (int element:arrayInput) {
            System.out.print(element+" ");
        }
        System.out.println();
        System.out.println(sum);
    }
}
