package Lab;

import java.util.Arrays;
import java.util.Scanner;

public class PB06_Equal_Arrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] inputFirstArray = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] inputSecondArray = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        boolean isSame = true;
        int indexPrint = 0,  sum = 0;

        if (inputFirstArray.length != inputSecondArray.length) {
            isSame = false;
            indexPrint = Math.min(inputFirstArray.length, inputSecondArray.length);
        } else {

            for (int index = 0; index <= inputFirstArray.length - 1; index++) {
                if (inputFirstArray[index] != inputSecondArray[index]) {
                    isSame = false;
                    indexPrint = index;
                    break;
                } else {
                    sum += inputFirstArray[index];
                }
            }
        }
        if (isSame){
            System.out.println("Arrays are identical. Sum: "+ sum);
        }else{
            System.out.println("Arrays are not identical. Found difference at "+indexPrint+ " index.");

        }
    }
}
