package Lab;

import java.util.Arrays;
import java.util.Scanner;

public class PB03_Sum_Even_Numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] inputArray = Arrays.stream(scanner.nextLine()
                        .split(" ")).mapToInt(Integer::parseInt)
                .toArray();
        int sumEven = 0;
        for (int index = 0; index <= inputArray.length - 1; index++) {
            if (inputArray[index] != 0 && inputArray[index] % 2 == 0) {
                sumEven += inputArray[index];
            }
        }
        System.out.println(sumEven);
    }
}
