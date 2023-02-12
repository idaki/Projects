package Exercises;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PB04_List_Operations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        String command = "";
        while (!"End".equals(command = scanner.nextLine())) {
            boolean isValidIndex = true;
            if (command.contains("Add")) {
                int element = Integer.parseInt(command.split("\\s+")[1]);
                nums.add(element);
            } else if (command.contains("Insert")) {
                int element = Integer.parseInt(command.split("\\s+")[1]);
                int index = Integer.parseInt(command.split("\\s+")[2]);
                if (index > nums.size()-1 || index < 0 ) {
                    System.out.println("Invalid index");
                    continue;
                }
                nums.add(index, element);
            } else if (command.contains("Remove")) {
                int element = Integer.parseInt(command.split("\\s+")[1]);
                if (element > nums.size()-1 || element<0) {
                    System.out.println("Invalid index");
                    continue;
                }
                nums.remove(element);
            } else if (command.contains("Shift left")) {
                int count = Integer.parseInt(command.split("\\s+")[2]);
                for (int i = 0; i < count; i++) {
                    nums.add(nums.get(0));
                    nums.remove(nums.get(0));
                }
            } else if (command.contains("Shift right")) {
                int count = Integer.parseInt(command.split("\\s+")[2]);
                for (int i = 0; i < count; i++) {
                    nums.add(0, nums.get(nums.size() - 1));
                    nums.remove(nums.size() - 1);
                }
            }
        }

        System.out.println(nums.toString().replaceAll("[\\[\\],]",""));
    }
}
