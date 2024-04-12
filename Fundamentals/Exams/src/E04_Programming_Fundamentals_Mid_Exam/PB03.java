package E04_Programming_Fundamentals_Mid_Exam;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PB03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = Arrays.stream(scanner.nextLine().split("@"))
                .map(Integer::parseInt).collect(Collectors.toList());
        String command = "";
        int currentIndex = 0 , countSuccess = 0;
        while (!"Love!".equals(command= scanner.nextLine())){
            String[] instruction = command.split(" ");
            int jump = Integer.parseInt(instruction[1]);
            if (jump<0){
                continue;
            }
            int newIndex = currentIndex + jump;
            if (newIndex > nums.size()-1){
               newIndex = 0;
            }
            currentIndex = newIndex;
            if ((nums.get(currentIndex)== 0)){
                System.out.printf("Place %d already had Valentine's day.%n",currentIndex);
                continue;
            }
            nums.set(currentIndex, nums.get(currentIndex)-2);

            if (nums.get(currentIndex)== 0){
                System.out.printf("Place %d has Valentine's day.%n",currentIndex);
                countSuccess++;
            }
        }
        System.out.printf("Cupid's last position was %d.%n", currentIndex);
        int countFail = nums.size()-countSuccess;
        if (countSuccess == nums.size()){
            System.out.println("Mission was successful.");
        }else {
            System.out.printf("Cupid has failed %d places.%n", countFail);
        }

    }
}
