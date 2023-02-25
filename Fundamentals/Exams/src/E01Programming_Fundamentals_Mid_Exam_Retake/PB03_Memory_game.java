package E01Programming_Fundamentals_Mid_Exam_Retake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PB03_Memory_game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> nums = Arrays.stream(scanner.nextLine()
                .split("\\s+")).collect(Collectors.toList());
        int counterMoves = 0;
        String inputIndex = "";
        boolean isOver = false;

        while (!"end".equals(inputIndex = scanner.nextLine())) {
            counterMoves++;
            List<String> indexList = Arrays.stream(inputIndex.split("\\s+")).collect(Collectors.toList());
            int index1 = Integer.parseInt(indexList.get(0));
            int index2 = Integer.parseInt(indexList.get(1));

            if (index1 == index2 || index1 < 0 || index1 > nums.size() - 1 || index2 < 0 || index2 > nums.size() - 1) {
                nums.add(nums.size() / 2, -counterMoves + "a");
                nums.add(nums.size() / 2, -counterMoves + "a");
                System.out.println("Invalid input! Adding additional elements to the board");
                continue;
            }

            if (nums.get(index1).equals(nums.get(index2))) {
                System.out.printf("Congrats! You have found matching elements - %s!%n", nums.get(index1));
                nums.remove(index1);
                if (index1<index2){
                nums.remove(index2-1);
                }else{
                    nums.remove(index2);
                }
            } else{
                System.out.println("Try again!");
            }

            if (nums.size() == 0) {
                isOver = true;
                break;
            }
        }
        if (isOver) {
            System.out.printf("You have won in %d turns!", counterMoves);
        } else {
            System.out.println("Sorry you lose :(");
            System.out.println(String.join(" ",nums));
        }
    }
}
