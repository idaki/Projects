package Exercises;

import java.util.Arrays;
import java.util.Scanner;

public class PB06_Equal_Sums { public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int[] arrayInput = Arrays.stream(scanner.nextLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
    int result =0;
    boolean isOneElement = false, isEqual = false;

    if  (arrayInput.length > 1) {
//0 1 2 3
        for (int index = 0; index <= arrayInput.length - 1; index++) {
            int sumLeft = 0, sumRight = 0;
            //sum right
            for (int i = index + 1; i <= arrayInput.length - 1; i++) {
                sumRight += arrayInput[i];
            }
            //sumLeft
            for (int i = 0; i < index; i++) {
                sumLeft += arrayInput[i];

            }
           if ( sumLeft ==sumRight){
               isEqual = true;
               result = index;
           }
        }
    }else {
        isOneElement = true;
    }

    if (isEqual){
        System.out.println(result);
    } else if (isOneElement) {
        System.out.println("0");
    } else {
        System.out.println("no");
    }
}
}
