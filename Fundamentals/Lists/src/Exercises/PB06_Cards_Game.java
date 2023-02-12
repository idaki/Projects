package Exercises;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PB06_Cards_Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> deck1 = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> deck2 = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        for (int i = 0; i <= Math.min(deck1.size() - 1, deck2.size() - 1); i++) {
            if (deck1.get(i) > deck2.get(i)) {
                deck1.add(deck1.get(i));
                deck1.add(deck2.get(i));
                deck1.remove(i);
                deck2.remove(i);
                i = -1;
                int result = getDeckSum(deck1);
                if (deck1.size() == 2) {
                    break;
                }
            } else if (deck1.get(i) < deck2.get(i)) {
                deck2.add(deck2.get(i));
                deck2.add(deck1.get(i));
                deck2.remove(i);
                deck1.remove(i);
                i = -1;
                if (deck2.size() == 2) {
                    break;
                }
            }else{
                deck1.remove(i);
                deck2.remove(i);
                i=-1;
            }
        }
       if (deck1.size()>deck2.size()){
        System.out.println("First player wins! Sum: "+getDeckSum(deck1));
       }else {
           System.out.println("Second player wins! Sum: "+getDeckSum(deck2));
       }
    }

    private static int getDeckSum(List<Integer> nums) {
        int result = 0;
        for (int element : nums) {
            result += element;
        }
        return result;
    }
}