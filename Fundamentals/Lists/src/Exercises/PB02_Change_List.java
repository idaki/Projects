package Exercises;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PB02_Change_List {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        String command = "";
        while (!"end".equals(command = scanner.nextLine())) {
        if (command.contains("Delete")){
            int removeValue = Integer.parseInt(command.split(" ")[1]);
            nums.removeAll(Collections.singleton(removeValue));
            }else {
            int element = Integer.parseInt(command.split(" ")[1]);
            int index = Integer.parseInt(command.split(" ")[2]);
            nums.add(index, element);
        }
        }
        System.out.println(nums.toString().replaceAll("[\\[\\],]",""));
    }
}
