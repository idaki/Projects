package E03;

import java.util.Scanner;

public class p01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();

        String commandInput = "";

        while (!"Reveal".equals(commandInput = scanner.nextLine())) {
            String[] commandArr = commandInput.split("\\:\\|\\:");
            String token = commandArr[1];
            if (commandInput.contains("InsertSpace")) {
                int index = Integer.parseInt(token);

            } else if (commandInput.contains("Reverse")) {

            } else if (commandInput.contains("ChangeAll")) {

            }

            //InsertSpace:|:{index}":
            //o	Inserts a single space at the given index. The given index will always be valid.
            //•	"Reverse:|:{substring}":
            //o	If the message contains the given substring, cut it out, reverse it and add it at the end of the message.
            //o	If not, print "error".
            //o	This operation should replace only the first occurrence of the given substring if there are two or more occurrences.
            //•	"ChangeAll:|:{substring}:|:{replacement}":
            //o	Changes all occurrences of the given substring with the replacement text.

        }


    }
}
