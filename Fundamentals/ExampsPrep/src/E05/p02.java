package E05;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        BigInteger tresHold = new BigInteger("1");
        for (char element : text.toCharArray()) {
            if (Character.isDigit(element)) {
                int digit = Character.getNumericValue(element);
                tresHold = tresHold.multiply(BigInteger.valueOf(digit));

            }
        }

        String regex = "(?<limiter>\\:\\:|\\*\\*)(?<emoji>[A-Z][a-z]{2,})\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int countMatches= 0;
        List<String> coolEmojiesList = new ArrayList<>();




        while (matcher.find()) {
            countMatches++;
            BigInteger sum = BigInteger.valueOf(0);
            String currentMatch = matcher.group("emoji");
            String limiter= matcher.group("limiter");
            for (char element : currentMatch.toCharArray()) {
                int charValue = (int) element;
                BigInteger charValueBig = BigInteger.valueOf(charValue);
                sum = sum.add(charValueBig);
            }

            int compareValue = sum.compareTo(tresHold);
            if (compareValue >=0){
                coolEmojiesList.add(limiter+currentMatch+limiter);
            }


        }
        System.out.println("Cool threshold: " + tresHold);
        System.out.println(countMatches + " emojis found in the text. The cool ones are:");
        coolEmojiesList.forEach(element-> System.out.println(element));

    }
}
