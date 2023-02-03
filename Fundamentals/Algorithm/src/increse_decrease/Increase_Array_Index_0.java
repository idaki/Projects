package increse_decrease;

import java.util.Arrays;
import java.util.Scanner;

public class Increase_Array_Index_0 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] inputArray = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int inputElement = Integer.parseInt(scanner.nextLine());


//1 2 3 4 => 0 1 2 3 4 (input element can be added to index 0)
            int[] temp = new int[inputArray.length + 1];
            for (int index = 0; index <= inputArray.length - 1; index++) {
                temp[index + 1] = inputArray[index];
            }
            inputArray = temp;


        for (int index = 0; index <= inputArray.length - 1; index++) {
            System.out.print(inputArray[index]+" ");
        }
    }
}
