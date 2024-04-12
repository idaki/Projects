package Exercises;

import java.util.Arrays;
import java.util.Scanner;

public class PB05_Top_Integers { public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int[] arrayInput = Arrays.stream(scanner.nextLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

    //1 4 3 2

    for (int index = 0; index < arrayInput.length - 1 ; index++) {
        for (int i = index+1; i <= arrayInput.length - 1 ; i++) {
            if (arrayInput[index] < arrayInput[i]){
                break;
            } else if (i == arrayInput.length-1 ) {
                System.out.print(arrayInput[index]+" ");
            }
        }
    }
    System.out.println(arrayInput[arrayInput.length - 1]);
}
}
