package Lab;

import java.util.Arrays;
import java.util.Scanner;

public class PB07_Condense_Array_to_Number {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] inputArray = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        // 0 1 2
        while (inputArray.length!=2) {
            int[] condensedArray = new int[inputArray.length-1];

            for (int index = 0; index < inputArray.length - 1; index++) {
               condensedArray[index]= inputArray[index]+inputArray[index+1];
            }
            inputArray = condensedArray;
        }
        System.out.println(inputArray[0]+inputArray[1]);
    }
}
