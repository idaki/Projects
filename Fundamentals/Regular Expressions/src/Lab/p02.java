package Lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        String regex ="\\+359([ -])2\\1\\d{3}\\1\\d{4}\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userInput);
        List< String> validNumberList= new ArrayList<>();

        while(matcher.find()){
           validNumberList.add(matcher.group());
        }
        System.out.println(validNumberList.toString().replaceAll("[\\[\\]]",""));
    }
}
