import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
      int sum = 0;
        List<Integer> nums = new ArrayList<>(Arrays.asList(1,2,3,4,5));
       while (nums.iterator().hasNext()){
         int currentNum = nums.iterator().next();
         if (currentNum - nums.get(0) == 0){
             currentNum= nums.iterator().next();
             System.out.println(currentNum);
         }
      //     System.out.println(currentNum);
       }

    }

}