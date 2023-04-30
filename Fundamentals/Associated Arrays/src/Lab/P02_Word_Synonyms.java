package Lab;

import java.util.*;

public class p02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, String> wordList = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String word = scanner.nextLine();
            String synonym = scanner.nextLine();
            if ( wordList.containsKey(word)){
            wordList.put(word,String.join(", ", wordList.get(word),synonym));
            }else{
                wordList.put(word,synonym);
            }
        }

        for (Map.Entry<String, String> entry : wordList.entrySet()){
            System.out.printf("%s - %s%n", entry.getKey(),entry.getValue());

        }



    }
}
