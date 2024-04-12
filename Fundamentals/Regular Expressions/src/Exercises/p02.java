package Exercises;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class p02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> participantsList = Arrays.stream(scanner.nextLine().split(","))
                                        .collect(Collectors.toList());
        String input="";
        String regexLetters= "[A-Za-z]+";
        String regexDistance="[0-9]";
        Pattern patternLetters = Pattern.compile(regexLetters);
        Pattern patternDistance = Pattern.compile(regexDistance);
        Map<String, Integer> participantsMap = new LinkedHashMap<>();
        while(!"end of race".equals(input = scanner.nextLine())){
            Matcher matcherLetters= patternLetters.matcher(input);
            StringBuilder name = new StringBuilder();
            while (matcherLetters.find()){
                name.append(matcherLetters.group());
            }
            String currentName= name.toString();

            int currentDistance = 0;
            Matcher matcherDistance = patternDistance.matcher(input);
            while (matcherDistance.find()){
                currentDistance += Integer.parseInt(matcherDistance.group());
            }
            if (participantsList.contains(currentName)){
               if (!participantsMap.containsKey(currentName)){
                participantsMap.put(currentName,currentDistance);
            } else{
                   int totalDistance = participantsMap.get(currentName);
                   participantsMap.put(currentName,totalDistance+currentDistance);
               }
            }
        }
        System.out.println("1st place: "+ participantsList.get(0));
        System.out.println("2nd place: "+ participantsList.get(1).trim());
        System.out.println("3rd place: "+ participantsList.get(2).trim());
    }
}
