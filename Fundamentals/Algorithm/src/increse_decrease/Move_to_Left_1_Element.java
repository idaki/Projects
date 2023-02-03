package increse_decrease;

import java.util.Arrays;
import java.util.Scanner;

public class Move_to_Left_1_Element {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] inputArray = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
// 1 2 3 4 => 2 3 4 1
        int temp = inputArray[0];
        for (int index = 0; index < inputArray.length - 1; index++) {
            inputArray[index] = inputArray[index+1];
        }
        inputArray[inputArray.length - 1]= temp;


//print

        for (int i = 0; i <= inputArray.length-1 ; i++) {
            System.out.print(inputArray[i]+" ");
        }


    }
}
