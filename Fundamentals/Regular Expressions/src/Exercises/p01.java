package Exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double totalSum = 0;
        String input ="";
        String regex =">>(?<name>[A-Za-z]+)<<(?<price>[0-9]+\\.?[0-9]+)\\!(?<quantity>[0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        List<String> furnitureList = new ArrayList<>();

        while(!"Purchase".equals(input = scanner.nextLine())){
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
            String furnitureName = matcher.group("name");
            double currentPrice = Double.parseDouble(matcher.group("price"));
            int currentQuantity = Integer.parseInt(matcher.group("quantity"));
            totalSum = totalSum + currentPrice * currentQuantity;
            furnitureList.add(furnitureName);
            }
        }

        System.out.println("Bought furniture:");
        furnitureList.forEach(element-> System.out.println(element));
        System.out.printf("Total money spend: %.2f", totalSum);

    }
}
