package E05;

import java.util.Scanner;

public class p01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rawActivationKey = scanner.nextLine();
        String command = "";

        while (!"Generate".equals(command = scanner.nextLine())) {
            String[] commandArr = command.split(">>>");
            if (command.contains("Contains")) {
                String substring = commandArr[1];
                if (rawActivationKey.contains(substring)) {
                    System.out.println(rawActivationKey + " contains " + substring);
                } else {
                    System.out.println("Substring not found!");
                }

            } else if (command.contains("Flip")) {
                String type = commandArr[1];
                int startIndex = Integer.parseInt(commandArr[2]);
                int endIndex = Integer.parseInt(commandArr[3]);
                StringBuilder temp = new StringBuilder();
                temp.append(rawActivationKey);
                String substring = rawActivationKey.substring(startIndex, endIndex);

                if ("Upper".equals(type)) {
                    substring = substring.toUpperCase();

                } else {
                    substring = substring.toLowerCase();
                }

                temp.replace(startIndex,endIndex,substring);

                rawActivationKey = temp.toString();

                System.out.println(rawActivationKey);


            } else if (command.contains("Slice")) {
                int startIndex = Integer.parseInt(commandArr[1]);
                int endIndex = Integer.parseInt(commandArr[2]);
                String substring = rawActivationKey.substring(startIndex, endIndex);
                StringBuilder temp = new StringBuilder();
                temp.append(rawActivationKey);
                temp.delete(startIndex, endIndex);
                rawActivationKey = temp.toString();
                System.out.println(rawActivationKey);
            }
        }
        System.out.println("Your activation key is: "+rawActivationKey);

    }
}
