package Exercises;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PB05_Bomb_Numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> bombNums = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());
       while (nums.contains(bombNums.get(0))){
        if (nums.size() >= bombNums.get(1)) {
            int bombIndex = nums.indexOf(bombNums.get(0));
            int bombEffect = bombNums.get(1);


            for (int i = 0; i < bombEffect; i++) {
                if (bombIndex == 0) {
                    break;
                }
                nums.remove(bombIndex - 1);
                bombIndex -= 1;
            }

            for (int i = 0; i < bombEffect; i++) {
                if (bombIndex == nums.size() - 1) {
                    break;
                }
                nums.remove(bombIndex + 1);
            }

            nums.remove(bombIndex);
        }
       }

        int sum = 0;
        for (int element : nums) {
            sum += element;
        }
        System.out.println(sum);

    }
}
