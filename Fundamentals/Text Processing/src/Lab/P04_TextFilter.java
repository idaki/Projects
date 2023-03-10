package Lab;

import java.util.*;

public class P04_TextFilter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] keyArr = scanner.nextLine().split(", ");
        Map<String, String> keyMap = new LinkedHashMap<>();
        String text = scanner.nextLine();

        for (int i = 0; i <= keyArr.length - 1; i++) {
            String word = keyArr[i];
            for (int j = 0; j <= word.length() - 1; j++) {
                if (!keyMap.containsKey(word)) {
                    keyMap.put(word, "*");
                } else {
                   // String code = keyMap.get(word)+ "*";
                    String code = String.join("",keyMap.get(word),"*");
                    keyMap.put(word,code);
                }
            }
        }
        for (Map.Entry<String, String> entry : keyMap.entrySet()) {
            text = text.replace(entry.getKey(), entry.getValue());
        }
        System.out.println(text);


    }
}
