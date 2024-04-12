package E01_Programming_Fundamentals_Final_Exam_Retake;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String regex = "(#?\\|?)(?<product>[A-Za-z]+ ?[A-Za-z]+)\\1(?<date>\\d{2}\\/\\d{2}\\/\\d{2})\\1(?<calories>\\d+)\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        List<String> printList = new ArrayList<>();

        int sumCalories = 0;
        while(matcher.find()){
            int currentCalories = Integer.parseInt(matcher.group("calories"));
            String product = matcher.group("product");
            String expirationDate = matcher.group("date");
            String printString = String.format("Item: %s, Best before: %s, Nutrition: %d"
                    ,product,expirationDate,currentCalories);
            sumCalories += currentCalories;
            printList.add(printString);
        }
        int countDays =0;
        int printCalories = sumCalories;

       while (sumCalories >=2000){
           sumCalories-=2000;
           countDays++;
       }
        System.out.printf("You have food to last you for: %d days!%n",countDays);
       printList.forEach(element-> System.out.println(element));



    }
}
