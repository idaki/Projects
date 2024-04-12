package Lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PB04_List_Manipulation_Basics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        String commandStr = "";

        while (!"end".equals(commandStr = scanner.nextLine())) {
           List<String> command = Arrays.stream(commandStr.split(" "))
                   .collect(Collectors.toList());

            switch (command.get(0)){
                case "Add":
                    int numToAdd = Integer.parseInt(command.get(1));
                    nums.add(numToAdd);
                    break;
                case "Remove":
                    int numToRemove = Integer.parseInt(command.get(1));
                    nums.remove(Integer.valueOf(numToRemove));
                    break;
                case "RemoveAt":
                    numToRemove = Integer.parseInt(command.get(1));
                    nums.remove(numToRemove);
                    break;
                case "Insert":
                    numToAdd = Integer.parseInt(command.get(1));
                    int index = Integer.parseInt(command.get(2));
                    nums.add(index, numToAdd);
                    break;
            }

        }
        System.out.println(nums.toString().replaceAll("[\\[\\],]",""));

    }
}
