package Lab;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PB01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Double> numbers = Arrays.stream(scanner.nextLine().split(" "))
                .map(Double::parseDouble)
                .collect(Collectors.toList());

//8 2 2 4 8 16
        for (int index = 0; index < numbers.size()-1; index++) {
            if (numbers.get(index).equals(numbers.get(index + 1))) {
                numbers.set(index, numbers.get(index) + numbers.get(index + 1));
                numbers.remove(index + 1);
                index = -1;
            }

        }
        String output = getElementsByDelimiter(numbers, " ");
        System.out.print(output);
    }

    private static String getElementsByDelimiter(List<Double> items, String delimiter) {
        String output = "";

        for (Double element : items) {
            output += (new DecimalFormat("0.#").format(element)+ delimiter);

        }
        return output;
    }
}
