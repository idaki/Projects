package E04_Programming_Fundamentals_Mid_Exam;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PB02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> groceriesList = Arrays.stream(scanner.nextLine().split("!")).collect(Collectors.toList());
        String command = "";

        while (!"Go Shopping!".equals(command = scanner.nextLine())) {
            List<String> commandList = Arrays.stream(command.split("\\s+")).collect(Collectors.toList());

            if (command.contains("Urgent")) {
               if (!groceriesList.contains(commandList.get(1))){
                groceriesList.add(0, commandList.get(1));
               }
            } else if (command.contains("Unnecessary")) {
                if (groceriesList.contains(commandList.get(1))){
                    groceriesList.remove(commandList.get(1));
                }

            } else if (command.contains("Correct")) {
                if (groceriesList.contains(commandList.get(1))) {
                    int index = commandList.indexOf(commandList.get(1));
                    groceriesList.set(index, commandList.get(2));
                }
            } else if (command.contains("Rearrange")){
                if (groceriesList.contains(commandList.get(1))) {
                    groceriesList.remove(commandList.get(1));
                    groceriesList.add(commandList.get(1));
                }
            }
        }
        System.out.println(String.join(", ",groceriesList));
    }
}
