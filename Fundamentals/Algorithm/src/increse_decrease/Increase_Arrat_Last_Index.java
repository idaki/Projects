package increse_decrease;

import java.util.Arrays;
import java.util.Scanner;

public class Increase_Arrat_Last_Index {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            int[] inputArray = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int inputElement = Integer.parseInt(scanner.nextLine());


//1 2 3 4 => 0 1 2 3 4 0 (input element can be added to index 0)
            int[] temp = new int[inputArray.length + 1];
            for (int index = 0; index <= inputArray.length - 1; index++) {
                temp[index] = inputArray[index];
            }
            inputArray = temp;


            for (int index = 0; index <= inputArray.length - 1; index++) {
                System.out.print(inputArray[index]+" ");
            }
        }
}
