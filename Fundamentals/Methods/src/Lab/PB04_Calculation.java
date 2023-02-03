package Lab;

import java.util.Scanner;

public class PB04_Calculation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String commandInput = scanner.nextLine();
        int integerOneInput = Integer.parseInt(scanner.nextLine());
        int integerTwoInput = Integer.parseInt(scanner.nextLine());

        if ("add".equals(commandInput)){
                printSum(integerOneInput,integerTwoInput);
        }else if ("multiply".equals(commandInput)){
            printMultiplication(integerOneInput,integerTwoInput);
        }else if ("subtract".equals(commandInput)){
            printSubtraction(integerOneInput,integerTwoInput);
        }else{
            printDivision(integerOneInput,integerTwoInput);
        }
    }
    public static void printSum(int integerOne, int integerTwo){
        System.out.println(integerOne+integerTwo);
    }
    public static void printMultiplication(int integerOne, int integerTwo){
        System.out.println(integerOne*integerTwo);
    }
    public static void printSubtraction(int integerOne, int integerTwo){
        System.out.println(integerOne-integerTwo);
    }
    public static void printDivision(int integerOne, int integerTwo){
       if (integerTwo !=0) {
           System.out.println(integerOne / integerTwo);
       }
    }
}
