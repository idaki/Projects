package Compare;

import java.util.Scanner;

public class Compare_StringA_toStringB {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] stringB = scanner.nextLine().split(" ");
        String[] stringA = scanner.nextLine().split(" ");
        String outputString = "";

        for (int index = 0; index <= stringA.length - 1; index++) {
            for (int i = 0; i <=stringB.length-1; i++) {
                if (stringA[index].equals(stringB[i])){
                    //System.out.print(stringA[index]);
                    outputString += String.format("%s ", stringA[index]);
                }
            }
        }
        System.out.println(outputString);
    }
}
