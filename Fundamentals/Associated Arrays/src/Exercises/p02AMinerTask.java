package Exercises;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p02AMinerTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputItem = "";
        Map<String, Integer> resources = new LinkedHashMap<>();
        while (!"stop".equals(inputItem = scanner.nextLine())) {

            int quantityAdded = Integer.parseInt(scanner.nextLine());
            if (!resources.containsKey(inputItem)) {
                resources.put(inputItem, quantityAdded);
            } else {
                int currentQuantity = resources.get(inputItem);
                resources.put(inputItem, currentQuantity + quantityAdded);
            }

        }
        resources.forEach((key, value) -> System.out.println(key + " -> " + value));

    }
}
