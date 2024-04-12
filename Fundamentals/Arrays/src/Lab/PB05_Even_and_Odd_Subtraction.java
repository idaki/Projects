package Lab;

import java.util.Arrays;
import java.util.Scanner;

public class PB05_Even_and_Odd_Subtraction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] inputArray = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    int sumOdd = 0,  sumEven = 0;

        for (int index = 0; index <= inputArray.length - 1 ; index++) {
            if (inputArray[index] !=0 &&inputArray[index] % 2 ==0) {
            sumEven = sumEven+ inputArray[index];
            }else {
                sumOdd = sumOdd+ inputArray[index];
            }
        }
        System.out.println(sumEven-sumOdd);
    }
}
