package E01Programming_Fundamentals_Mid_Exam_Retake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PB02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numPeople = Integer.parseInt(scanner.nextLine());
        int[] liftState = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        List<Integer> printResult = new ArrayList<>();


        for (int i = 0; i <= liftState.length - 1; i++) {
            if (liftState[i] <0){
                liftState[i] = 0;
            }
            int transportedPeople = 4 - liftState[i];
            int previousNumPeople = numPeople;
            if (numPeople> 0) {
                numPeople -= transportedPeople;
                if (numPeople <= 0) {
                    transportedPeople = previousNumPeople;
                }
                printResult.add(transportedPeople + liftState[i]);
                previousNumPeople = numPeople;
            } else if ( i <= liftState.length-1) {
                printResult.add( liftState[i]);
            }

        }
        if (numPeople <= 0 && printResult.get(printResult.size() - 1) < 4) {
            System.out.println("The lift has empty spots!");
            System.out.println(printResult.toString().replaceAll("[\\[\\],]", ""));

        } else if (numPeople>0 && printResult.get(printResult.size() - 1) == 4) {
            System.out.printf("There isn't enough space! %d people in a queue!%n",numPeople);
            System.out.println(printResult.toString().replaceAll("[\\[\\],]", ""));
        }else {
            System.out.println(printResult.toString().replaceAll("[\\[\\],]", ""));
        }
    }
}
