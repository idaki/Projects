package Lab;

import java.text.DecimalFormat;
import java.util.Scanner;

public class PB08_Math_Power {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double numberOneInput = Double.parseDouble(scanner.nextLine());
        double numberTwoInput = Double.parseDouble(scanner.nextLine());
        DecimalFormat df = new DecimalFormat("#.####");
        double output = getPowerResult(numberOneInput, numberTwoInput);

        System.out.println(df.format(output));
    }
    public static double getPowerResult (double numOne, double numTwo){
        double result = Math.pow(numOne,numTwo);

        return result;
    }
}
