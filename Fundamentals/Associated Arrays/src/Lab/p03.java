package Lab;

import java.util.*;
import java.util.stream.Collectors;

public class p03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> wordList = Arrays.stream(scanner.nextLine().split("\\s+")).map(String::toLowerCase).collect(Collectors.toList());

        Map<String, Integer> wordMap = new LinkedHashMap<>();
        for (String word: wordList) {
            if (!wordMap.containsKey(word)){
                wordMap.put(word,0);
            }
            wordMap.put(word, wordMap.get(word)+1);
        }
        List< String> print = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()){
            if (entry.getValue() % 2 !=0) {
                print.add(entry.getKey());
            }
        }
        System.out.println(print.toString().replaceAll("[\\[\\]]",""));




    }
}
