package E03_Programming_Fundamentals_Mid_Exam_Retake;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PB02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String regex = "(#|@)(?<wordOne>[A-Za-z]{3,})\\1\\1(?<wordTwo>[A-Za-z]{3,})\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        int countWords = 0;
        List <String> printWordsList = new ArrayList<>();

        while (matcher.find()) {
            countWords++;
            StringBuilder firstWord = new StringBuilder(matcher.group("wordOne"));
            StringBuilder secondWord = new StringBuilder(matcher.group("wordTwo"));

            if (firstWord.toString().equals(secondWord.reverse().toString())){
                String currentMatch = String.format(firstWord + " <=> " + secondWord.reverse());
                printWordsList.add(currentMatch);
            }
        }

        if (!printWordsList.isEmpty()) {
            System.out.println(countWords+ " word pairs found!");
            System.out.println("The mirror words are: ");
            System.out.println(printWordsList.toString().replaceAll("[\\[\\]]", ""));
        }else if(countWords>0){
            System.out.println(countWords + " word pairs found!");
            System.out.println("No mirror words!");
        }else {
            System.out.println("No word pairs found!");
            System.out.println("No mirror words!");
        }


    }
}