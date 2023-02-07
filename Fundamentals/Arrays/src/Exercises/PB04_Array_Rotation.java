package Exercises;

import java.util.Scanner;

public class PB04_Array_Rotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputArray = scanner.nextLine().split(" ");
        int rotationNumber = Integer.parseInt(scanner.nextLine());
        //0 1 2 3 4 => 1 2 3 4 0 =>2 3 4 0 1

        for (int i = 0; i <rotationNumber; i++) {
            String temp = inputArray[0];
            for (int index = 1; index <= inputArray.length - 1; index++) {
                inputArray[index - 1] = inputArray[index];
            }
            inputArray[inputArray.length - 1] = temp;
        }
        for (String element: inputArray) {
            System.out.print(element+ " ");

        }
    }
}
