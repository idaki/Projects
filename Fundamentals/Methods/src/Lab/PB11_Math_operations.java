package Lab;

import java.text.DecimalFormat;
import java.util.Scanner;

public class PB11_Math_operations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int firstNumberInput = Integer.parseInt(scanner.nextLine());
        String operator = scanner.nextLine();
        int secondNumberInput = Integer.parseInt(scanner.nextLine());
        DecimalFormat df = new DecimalFormat("#.##");

        switch(operator){
            case "/":
                double output =getDivision(firstNumberInput,secondNumberInput);
                System.out.println(df.format(output));
                break;
            case "*":
                output =getMultiply(firstNumberInput,secondNumberInput);
                System.out.println(df.format(output));
                break;
            case "+":
                output =getSum(firstNumberInput,secondNumberInput);
                System.out.println(df.format(output));
                break;
            case "-":
                output =getSubtract(firstNumberInput,secondNumberInput);
                System.out.println(df.format(output));
                break;
        }
    }
    public static double getDivision(double firstNumber, double secondNumber) {
        double result = 0;
        if (secondNumber != 0) {
            result = firstNumber / secondNumber;
        }
        return result;
    }
    public static double getMultiply(double firstNumber, double secondNumber){

        return firstNumber*secondNumber;
    }
    public static double getSum(double firstNumber, double secondNumber){
        return firstNumber + secondNumber;
    }
    public static double getSubtract(double firstNumber, double secondNumber){
        return firstNumber - secondNumber;
    }
}
