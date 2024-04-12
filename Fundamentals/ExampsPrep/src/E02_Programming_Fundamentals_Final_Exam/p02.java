package E02_Programming_Fundamentals_Final_Exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String regex = "(\\=|\\/)(?<destination>[A-Z][A-Za-z]+)\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        List<String> destinationList = new ArrayList<>();
        int travelPoints = 0;

        while (matcher.find()){
            int length = matcher.group("destination").length();
            String destinationName = matcher.group("destination");
            if (length>=3){
                destinationList.add(destinationName);
               travelPoints += length;
            }
        }
        System.out.print("Destinations: ");
        System.out.print(destinationList.toString().replaceAll("[\\[\\]]",""));
        System.out.println();
        System.out.println("Travel Points: "+ travelPoints);

    }
}
