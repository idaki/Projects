package E05_Programming_Fundamentals_Mid_Exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PB03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> inventory = Arrays.stream(scanner.nextLine().split(",\\s+"))
                .collect(Collectors.toList());

        String commandLine = "";
        while (!"Craft!".equals(commandLine = scanner.nextLine())) {

            List<String> commandList = Arrays.stream(commandLine.split(" - "))
                    .collect(Collectors.toList());
            String token  = commandList.get(1);
            if (commandList.contains("Collect")){
                if (!inventory.contains(token)) {
                    inventory.add(token);
                }
            } else if (commandList.contains("Drop")) {
                inventory.remove(token);
            }else if (commandList.contains("Combine Items")) {
               String[] tokenSpecial = token.split(":");
                if (inventory.contains(tokenSpecial[0])){
                    int index= inventory.indexOf(tokenSpecial[0]);
                    inventory.add(index+1,tokenSpecial[1]);
                }
            }else {
                if(inventory.contains(token)){
                    inventory.remove(token);
                    inventory.add(token);
                }
            }
        }
        String print = String.join(" ",inventory);
        print= print.replaceAll(" ",", ");
        System.out.println(print);
    }

}
