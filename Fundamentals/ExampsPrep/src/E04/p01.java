package E04;

import java.util.Scanner;

public class p01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rawPassword = scanner.nextLine();
        StringBuilder tempPassword = new StringBuilder();

        String command = "";

        while (!"Done".equals(command = scanner.nextLine())) {
            String[] commandArr = command.split(" ");
            if (command.contains("TakeOdd")) {
                for (int i = 0; i <= rawPassword.toCharArray().length - 1; i++) {
                    char currentChar = rawPassword.toCharArray()[i];
                    if (i % 2 != 0) {
                        tempPassword.append(currentChar);
                    }
                }
                rawPassword = tempPassword.toString();
                tempPassword = new StringBuilder();
                System.out.println(rawPassword);

            } else if (command.contains("Cut")) {

                int startIndex = Integer.parseInt(commandArr[1]);
                int length = Integer.parseInt(commandArr[2]);
                int endIndex = startIndex + length;

                String subString = rawPassword.substring(startIndex, endIndex);
                if (rawPassword.contains(subString)) {
                    rawPassword = rawPassword.replaceFirst(subString, "");
                    System.out.println(rawPassword);
                }

            } else if (command.contains("Substitute")) {
                String oldSubStr = commandArr[1];
                String newSubStr = commandArr[2];
               if (rawPassword.contains(oldSubStr)){
                rawPassword = rawPassword.replace(oldSubStr,newSubStr);
                System.out.println(rawPassword);
               }else {
                   System.out.println("Nothing to replace!");
               }
            }

        }
        String completePassword = rawPassword;
        System.out.println("Your password is: "+completePassword);

    }
}
