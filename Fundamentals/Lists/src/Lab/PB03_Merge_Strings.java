package Lab;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class PB03_Merge_Strings{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> nums1 = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        List<Integer> nums2 = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        List<Integer> result = new ArrayList<>();

//TODO: Read the input
        List<Integer> resultNums = new ArrayList<>();



        for (int i = 0; i < Math.min(nums1.size(), nums2.size()); i++) {

            //TODO: Add numbers in resultNums


        }
        if (nums1.size() > nums2.size()) {
           int listSize = nums2.size();
            resultNums.addAll(getCommonLengthElements(nums1, nums2, listSize));
            resultNums.addAll(getRemainingElements(nums1, nums2));
        }  else if (nums2.size() > nums1.size()) {
            int listSize= nums1.size();
            resultNums.addAll(getCommonLengthElements(nums1, nums2, listSize));
            resultNums.addAll(getRemainingElements(nums2, nums1));
        }
        System.out.println(resultNums.toString().replaceAll("[\\[\\],]", ""));
    }

    private static List<Integer> getCommonLengthElements (List<Integer> nums1, List<Integer> nums2, int listSize ) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i <= listSize-1; i++) {
            nums.add(nums1.get(i));
            nums.add(nums2.get(i));

        }
        return nums;
    }

    public static List<Integer> getRemainingElements
            (List<Integer> longerList, List<Integer> shorterList) {
        List<Integer> nums = new ArrayList<>();
        for (int i = shorterList.size(); i < longerList.size(); i++)
            nums.add(longerList.get(i));
        return nums;
    }

}