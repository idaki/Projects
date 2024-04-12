import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text= scanner.nextLine();

        String regex= "[@#]+(?<color>[a-z]{3,})[@#]+\\W*\\/*(?<amount>\\d+)\\/*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while(matcher.find()){
            String color = matcher.group("color");
            String eggsAmount = matcher.group("amount");
            System.out.printf("You found %s %s eggs!%n",eggsAmount,color);
        }
    }
}
