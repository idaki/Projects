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
        String regex = "[@#]([a-zA-Z]{3,})[@#]{2}\\1[@#]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        int countWords = 0;
        List <String> printWordsList = new ArrayList<>();

        while (matcher.find()) {

            String currentWordPair = matcher.group("word");
           currentWordPair = currentWordPair.replace("@","#");
            int wordLength = currentWordPair.length();
            int firstIndex = currentWordPair.indexOf("#");
            int lastIndex = currentWordPair.lastIndexOf("#");
            int firstWordLength = firstIndex;
            int secondWorLength = wordLength - 1 - lastIndex;

            if (firstWordLength>=3 && secondWorLength >=3) {
                countWords++;
                boolean isDifferent = false;
                StringBuilder firstWord = new StringBuilder();
                StringBuilder secondWord = new StringBuilder();
                for (int i = 0; i < wordLength  / 2 -1; i++) {
                    isDifferent = false;
                    char[] wordCharArr = currentWordPair.toCharArray();
                    char firstWordChar = wordCharArr[i];
                    char secondWordChar = wordCharArr[wordLength - 1 - i];
                    firstWord.append(firstWordChar);
                    secondWord.append(secondWordChar);

                    if ( firstWordChar != secondWordChar)  {
                        isDifferent = true;
                        break;
                    }
                }
                if (!isDifferent) {
                    lastIndex = wordLength / 2;
                    String currentMatch = String.format(firstWord + " <=> " + secondWord.reverse());
                    printWordsList.add(currentMatch);
                }
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