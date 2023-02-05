package Exercises;

import java.text.DecimalFormat;
import java.util.Scanner;

public class PB08_Factorial_Division {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int firstNumber = Integer.parseInt(scanner.nextLine());
        int secondNumber = Integer.parseInt(scanner.nextLine());
        double output = calculateFactorialFirstNumber(firstNumber)*1.0
                / calculateFactorialSecondNumber(secondNumber);
        DecimalFormat df = new DecimalFormat("#.##");
        output = Double.parseDouble(df.format(output));
        System.out.printf("%.2f",output);
    }

    public static long calculateFactorialFirstNumber (long factorial){
        int num = (int) factorial;
        for (int i = num ; i > 1 ; i--) {
           factorial *= (i-1);
        }
        return factorial;
    }
    public static long calculateFactorialSecondNumber (long factorial){
        int num = (int) factorial;
        for (int i = num ; i > 1 ; i--) {
            factorial *= (i-1);
        }
        return factorial;
    }

}
