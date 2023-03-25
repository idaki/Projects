package Exercises;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String regexInitialInput = "[STRAstra]";
        Pattern pattern = Pattern.compile(regexInitialInput);
        List<String> attackedPlanetList = new ArrayList<>();
        List<String> defendedPlanetList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            Matcher matcher = pattern.matcher(input);
            int count = (int) matcher.results().count();
            StringBuilder text = new StringBuilder();
            for (int j = 0; j <= input.length()-1; j++) {
                char currentChar = input.charAt(j);
                text.append((char) (currentChar - count));
            }
                String decodedInput= text.toString();

             //   String regexValidation = "\\@(?<name>[A-Z][a-z]+)[^\\@\\-\\!\\>\\:]:(?<population>[0-9]+)[^\\@\\-\\!\\>\\:]!(?<type>[AD])\\!\\-\\>(?<solierCount>\\d+)";
            String regexValidation = "@(?<name>[A-Za-z]+)[^@\\-!>:]*:(?<population>[0-9]+)[^@\\-!>:]*!(?<type>[A|D])!->(?<solierCount>\\d+)";
            Pattern patternValidation = Pattern.compile(regexValidation);
                Matcher matcherValidation = patternValidation.matcher(decodedInput);

             while (matcherValidation.find()) {
                    String typeAction = matcherValidation.group("type");

                    if (typeAction.equals("A")) {
                        attackedPlanetList.add(matcherValidation.group("name"));
                    } else if (typeAction.equals("D")) {
                        defendedPlanetList.add(matcherValidation.group("name"));
                    }
                }
            }

        Collections.sort(attackedPlanetList);
        Collections.sort(defendedPlanetList);

        System.out.println("Attacked planets: "+ attackedPlanetList.size());
        attackedPlanetList.forEach(element-> System.out.println("-> "+ element));
        System.out.println("Destroyed planets: "+ defendedPlanetList.size());
        defendedPlanetList.forEach(element-> System.out.println("-> "+ element));
    }
}
