package E01_Programming_Fundamentals_Final_Exam_Retake;

import java.util.Scanner;

public class p01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder text = new StringBuilder(scanner.nextLine());

        String input = "";

        while (!"Decode".equals(input = scanner.nextLine())) {
            String[] inputArr = input.split("\\|");
            String command = inputArr[0];

            if (command.contains("Move")) {
                int numLetters = Integer.parseInt(inputArr[1]);
                if (numLetters > 0 && numLetters<text.length()){
                    String subStr = text.substring(0,numLetters);
                    text.delete(0,numLetters);
                    text.append(subStr);
                }

            } else if (command.contains("Insert")) {
                int index = Integer.parseInt(inputArr[1]);
               String value = inputArr[2];
                if (index >=0 ){
                    while(index > text.length()){
                        text.append(" ");
                    }
                    text.insert(index,value);
                }
            } else {
                String oldElement = inputArr[1];
                String newElement = inputArr[2];
                String textStr = text.toString().replace(oldElement,newElement);
                text.delete(0, text.length());
                text.append(textStr);
            }
        }
        System.out.println("The decrypted message is: "+text);
    }
}
