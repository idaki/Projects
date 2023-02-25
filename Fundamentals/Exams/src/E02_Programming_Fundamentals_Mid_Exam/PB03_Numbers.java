package E02_Programming_Fundamentals_Mid_Exam;

import java.util.*;
import java.util.stream.Collectors;

public class PB03_Numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());
        int sum = 0;

        for (int element : nums) {
            sum += element;
        }
        double averageNums = (double) sum /nums.size();
        List<Integer> print = new ArrayList<>();
        for (int element : nums) {
            if (element > averageNums){
            print.add(element);
            }
        }
        Collections.sort(print, Collections.reverseOrder());
        if(print.size()>0 && print.size()<=5){

            System.out.println(print.toString().replaceAll("[\\[\\],]",""));
        } else if(print.size()>5){
            System.out.println(print.subList(0,5).toString().replaceAll("[\\[\\],]",""));
        }else{
            System.out.println("No");
        }


    }
}
