package Exercises;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PB07_Append_Arrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> nums = Arrays.stream(scanner.nextLine().split("\\|")).collect(Collectors.toList());
       Collections.reverse(nums);
        String print = nums.toString().replaceAll("[\\[\\],]","");
        print = print.replaceAll("\\s+"," ");


        System.out.println(print);

    }
}
