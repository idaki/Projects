package Lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String regex = "\\b[A-Z][a-z]+ [A-Z][a-z]+\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userInput);
        List<String> print = new ArrayList<>();
        while (matcher.find()){
            String name = matcher.group();
            print.add(name);

        }
        System.out.println(print.toString().replaceAll("[\\[\\],]",""));

    }
}
