package Lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PB05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        String commandStr = "";

        while (!"end".equals(commandStr = scanner.nextLine())) {
            List<String> command = Arrays.stream(commandStr.split(" "))
                    .collect(Collectors.toList());
            String print = "";

            switch (command.get(0)) {
                case "Contains":
                    int numInput = Integer.parseInt(command.get(1));
                    if (nums.contains(numInput)) {
                        System.out.println("Yes");
                    } else {
                        System.out.println("No such number");
                    }
                    break;
                case "Print":
                    System.out.println(getTypeOfNumber(command, nums).toString()
                            .replaceAll("[\\[\\],]", ""));
                    break;
                case "Get":
                    int sum = 0;
                    for (int element : nums) {
                        sum += element;
                    }
                    System.out.println(sum);
                    break;
                case "Filter":
                    System.out.println(getComparison(command, nums)
                            .toString().replaceAll("[\\[\\],]",""));
                    break;
            }
        }
    }

    private static List<Integer> getComparison(List<String> command, List<Integer> nums) {
        List<Integer> result = new ArrayList<>();
        int numToCheck = Integer.parseInt(command.get(2));
        //'<', '>', ">=", "<="
        //43 876 342 543
        switch (command.get(1)) {
            case "<":
                for (int i = 0; i <= nums.size() - 1; i++) {
                    if (nums.get(i) < numToCheck) {
                        result.add(nums.get(i));
                    }
                }
                break;
            case ">":
                for (int i = 0; i <= nums.size() - 1; i++) {
                    if (nums.get(i) > numToCheck) {
                        result.add(nums.get(i));
                    }
                }
                break;
            case ">=":
                for (int i = 0; i <= nums.size() - 1; i++) {
                    if (nums.get(i) >= numToCheck) {
                        result.add(nums.get(i));
                    }
                }
                break;
            case "<=":
                for (int i = 0; i <= nums.size() - 1; i++) {
                    if (nums.get(i) <= numToCheck) {
                        result.add(nums.get(i));
                    }
                }
                break;


        }
        return result;
    }

    private static List<Integer> getTypeOfNumber(List<String> type, List<Integer> nums) {
        List<Integer> result = new ArrayList<>();
        if ("even".equals(type.get(1))) {
            for (int i = 0; i <= nums.size() - 1; i++) {
                if (nums.get(i) % 2 == 0) {
                    result.add(nums.get(i));
                }
            }
        } else {
            for (int i = 0; i <= nums.size() - 1; i++) {
                if (nums.get(i) % 2 != 0) {
                    result.add(nums.get(i));
                }
            }
        }
        return result;
    }
}

