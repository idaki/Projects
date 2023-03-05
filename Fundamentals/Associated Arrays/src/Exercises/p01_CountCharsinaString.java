package Exercises;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p01_CountCharsinaString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine().replaceAll(" ", "");
        Map<Character, Integer> characterMap = new LinkedHashMap<>();
        for (char element : inputStr.toCharArray()) {
            if (!characterMap.containsKey(element)) {
                characterMap.put(element, 1);
            } else {
                int currentValue = characterMap.get(element);
                characterMap.put(element, currentValue + 1);
            }
        }
        characterMap.forEach((key, value) -> System.out.println(key + " -> " + value));


    }
}
