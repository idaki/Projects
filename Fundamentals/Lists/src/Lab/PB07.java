package Lab;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PB07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        for (int i = 0; i <= nums.size()-1; i++) {
            if (nums.get(i) < 0){
                nums.remove(i);
                i=-1;
            }
        }
        if (nums.size()==0){
            System.out.println("empty");
        } else {
        for (int i = nums.size()-1 ; i >=0; i--) {
            System.out.print(nums.get(i)+" ");
        }
        }
    }
}
