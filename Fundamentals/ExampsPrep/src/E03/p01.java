package E03;

import java.util.Scanner;

public class p01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        StringBuilder updatedMessage = new StringBuilder(message);

        String commandInput = "";

        while (!"Reveal".equals(commandInput = scanner.nextLine())) {
            String[] commandArr = commandInput.split(":\\|:");
            String token = commandArr[1];
            if (commandInput.contains("InsertSpace")) {
                int index = Integer.parseInt(token);
                if (index >= 0 && index <= updatedMessage.length() - 1) {
                    updatedMessage.insert(index, " ");
                }
            } else if (commandInput.contains("Reverse")) {

                if (updatedMessage.toString().contains(token)) {
                    int startIndex = updatedMessage.indexOf(token);
                    int endIndex = startIndex + token.length() - 1;
                    StringBuilder subString = new StringBuilder(updatedMessage.substring(startIndex, endIndex + 1));
                    subString.reverse();
                    updatedMessage.delete(startIndex, endIndex+1);
                    updatedMessage.append(subString);
                }else{
                    System.out.println("error");
                    continue;
                }
            } else if (commandInput.contains("ChangeAll")) {
                char existingElement =  token.charAt(0);
                char replacingElement = commandArr[2].charAt(0);
                String temp = updatedMessage.toString();
                temp = temp.replace(existingElement,replacingElement);
                updatedMessage.replace(0,updatedMessage.length(),temp);
            }
            System.out.println(updatedMessage);
        }

        System.out.println("You have a new text message: " + updatedMessage);

    }
}
