package increse_decrease;

import java.util.Arrays;
import java.util.Scanner;

public class Move_to_Right_1_Element {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] inputArray = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
// 1 2 3 4 => 4 1 2 3
        int temp = inputArray[inputArray.length-1];
        for (int index = inputArray.length - 1; index > 0; index--) {
            inputArray[index] = inputArray[index-1];
        }
        inputArray[0]= temp;

//print
        for (int i = 0; i <= inputArray.length-1 ; i++) {
            System.out.print(inputArray[i]+" ");
        }


    }
}
