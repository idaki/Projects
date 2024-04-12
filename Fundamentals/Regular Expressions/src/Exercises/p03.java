package Exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        double totalSum = 0;
        String regex ="%(?<name>[A-Z][a-z]+)%[^|$%.]*<(?<product>\\w+)>[^|$%.]*\\|(?<count>\\d+)\\|(?<text>[A-Za-z]*)(?<price>\\d+\\.?\\d+)\\$";
        Pattern pattern = Pattern.compile(regex);
        while(!"end of shift".equals(input = scanner.nextLine())){
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()){
               String name = matcher.group("name");
               String product = matcher.group("product");
               int count = Integer.parseInt(matcher.group("count"));
               double price = Double.parseDouble(matcher.group("price"));
               double sum = count*price;
               totalSum += sum;
               System.out.printf("%s: %s - %.2f%n",name, product,sum);
            }
        }
        System.out.printf("Total income: %.2f", totalSum);
    }
}
