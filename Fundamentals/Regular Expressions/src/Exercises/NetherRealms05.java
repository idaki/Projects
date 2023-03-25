package ProgrammingFundamentals.Regex.Exercise;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetherRealms05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regexSpit = ", ";
        String [] input = scanner.nextLine().split(regexSpit);

        int health = 0;
        int numMultiplications = 0;
        int numDividings = 0;


        for (int i = 0; i < input.length; i++) {
            health = 0;

            String firstWord = input[i];
            String regex = "(?<letters>[A-Za-z])";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(firstWord);

            while (matcher.find()) {
                String currentLetter = matcher.group("letters");
                char letter = currentLetter.charAt(0);
                int num = (int) letter;
                health += num;
            }

            String regex2 = "(?<multiplications>[*])";
            Pattern pattern2 = Pattern.compile(regex2);
            Matcher matcher2 = pattern2.matcher(firstWord);

            while (matcher2.find()) {
                numMultiplications++;
            }

            String regex3 = "(?<multiplications>[\\/])";
            Pattern pattern3 = Pattern.compile(regex3);
            Matcher matcher3 = pattern3.matcher(firstWord);

            while (matcher3.find()) {
                numDividings ++;
            }


            String regex4 = "(?<numbers>[\\-?0-9\\.?]+)";
            double damage = 0;
            double fullDamage = 0;

            Pattern pattern4 = Pattern.compile(regex4);
            Matcher matcher4 = pattern4.matcher(firstWord);

            while (matcher4.find()) {
                String num = (matcher4.group("numbers"));
                double number = Double.parseDouble(num);
                if (number < 0) {
                    damage = damage + number;
                } else {
                    damage += number;
                }
            }

            if (numMultiplications > 0) {
                fullDamage = damage * (numMultiplications * 2);
            }

            if (numDividings > 0) {
                fullDamage = damage / (numDividings * 2);
            }


            System.out.printf("%s - %d health, %.2f damage%n", firstWord, health, fullDamage);

        }


    }
}
