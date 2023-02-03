package increse_decrease;

import java.util.Arrays;
import java.util.Scanner;

public class Decrease_Array_Last_Index {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] inputArray = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int inputElement = Integer.parseInt(scanner.nextLine());


// 1 2 3 4 => 1 2 3 (decrease array by removing last index )
        int[] temp = new int[inputArray.length - 1];
        for (int index = 0; index < inputArray.length - 1; index++) {
            temp[index] = inputArray[index];
        }
        inputArray = temp;



        //print
        for (int index = 0; index <= inputArray.length - 1; index++) {
            System.out.print(inputArray[index]+" ");
        }
    }
}
