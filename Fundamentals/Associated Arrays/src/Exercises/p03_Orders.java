package Exercises;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p03_Orders {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        Map<String, Integer> productQuantityMap = new LinkedHashMap<>();
        Map<String, Double> productPriceMap = new LinkedHashMap<>();

        while (!"buy".equals(input = scanner.nextLine())) {
            String[] commandArr = input.split(" ");
            String productName = commandArr[0];
            double productPriceAdded = Double.parseDouble(commandArr[1]);
            int productQuantityAdded = Integer.parseInt(commandArr[2]);

            if (productQuantityMap.containsKey(productName)) {
                int currentProductQuantity = productQuantityMap.get(productName);
                productQuantityMap.put(productName, currentProductQuantity + productQuantityAdded);
                productPriceMap.put(productName, productPriceAdded);
            } else {
                productQuantityMap.put(productName, productQuantityAdded);
                productPriceMap.put(productName, productPriceAdded);
            }
        }

        for (Map.Entry <String, Integer> entry : productQuantityMap.entrySet()) {
            DecimalFormat df = new DecimalFormat("#.00#");
            double totalPrice = entry.getValue() * productPriceMap.get(entry.getKey());
            System.out.println(entry.getKey()+ " -> " + df.format(totalPrice));
        }

    }
}
