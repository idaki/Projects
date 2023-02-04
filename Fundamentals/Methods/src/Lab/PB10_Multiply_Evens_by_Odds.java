package Lab;

import java.util.Scanner;

public class PB10_Multiply_Evens_by_Odds {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int integerInput = Math.abs(Integer.parseInt(scanner.nextLine()));
        System.out.println(getMultipleOfEventAndOdds(integerInput));
    }

    public static int getMultipleOfEventAndOdds(int number) {
        int lastDigit = 0, sumOdd =0, sumEven = 0;
        for (int i =number; i >0; i--) {
           if (number  == 0 ){
               break;
           }
            if (number % 10 != 0) {
                lastDigit = number % 10;
                if (lastDigit % 2 == 0){
                    sumEven +=lastDigit;
                }else{
                    sumOdd +=lastDigit;
                }
            }
            number /= 10;
        }
        return sumOdd*sumEven;
    }
}
