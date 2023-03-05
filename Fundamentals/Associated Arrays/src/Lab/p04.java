package Lab;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class p04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = Arrays.stream(scanner.nextLine().split("\\s+")).filter(w -> w.length()%2==0)
                .toArray(String[]::new);

        for (String element: words) {
            System.out.println(element);
        }
    }
}
