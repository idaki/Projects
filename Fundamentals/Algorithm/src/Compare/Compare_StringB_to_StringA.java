package Compare;

import java.util.Scanner;

public class Compare_StringB_to_StringA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] stringA = scanner.nextLine().split(" ");
        String[] stringB = scanner.nextLine().split(" ");
        String outputString = "";

        for (int index = 0; index <= stringB.length - 1; index++) {
            for (int i = 0; i <=stringA.length-1; i++) {
                if (stringB[index].equals(stringA[i])){
                    //System.out.print(stringB[index]);
                    outputString += String.format("%s ", stringB[index]);
                }
            }
        }
        System.out.println(outputString);
    }
}
