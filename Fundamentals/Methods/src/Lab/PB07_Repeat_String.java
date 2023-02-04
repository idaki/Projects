package Lab;

import java.util.Scanner;

public class PB07_Repeat_String {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String textInput = scanner.nextLine();
        int numIterationsInput = Integer.parseInt(scanner.nextLine());

        System.out.println(addStringContent (textInput, numIterationsInput));

    }
    public static String addStringContent(String text, int iterations){
        String output = "";
        for (int i = 0; i <iterations ; i++) {
            output= String.format("%s%s",output,text);
        }
        return output;
    }

}
