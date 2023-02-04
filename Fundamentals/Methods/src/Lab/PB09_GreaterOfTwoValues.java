package Lab;

import java.util.Scanner;

public class PB09_GreaterOfTwoValues {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        switch (command) {
            case "int":
                int inputOneInt = Integer.parseInt(scanner.nextLine());
                int inputTwoInt = Integer.parseInt(scanner.nextLine());
                System.out.println(compareLength(inputOneInt, inputTwoInt));

                break;
            case "char":
                char inputCharOne = scanner.nextLine().charAt(0);
                char inputCharTwo = scanner.nextLine().charAt(0);
                System.out.println(compareLength(inputCharOne, inputCharTwo));
                break;

            case "string":
                String inputOneStr = scanner.nextLine();
                String inputTwoStr = scanner.nextLine();
                System.out.println(compareLength(inputOneStr, inputTwoStr));
                break;

        }

    }

    public static int compareLength(int inputOne, int inputTwo) {
        int result = Math.max(inputOne, inputTwo);
        return result;
    }

    public static char compareLength(char inputOne, char inputTwo) {

        if (inputOne > inputTwo){
            return inputOne;
        }else {
            return inputTwo;
        }
    }
    public static String compareLength(String inputOne, String inputTwo) {
        String result = "";

        if (inputOne.compareTo(inputTwo) > 0) {
            result = inputOne;
        } else {
            result = inputTwo;
        }
        return result;
    }
}
