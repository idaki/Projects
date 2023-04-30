package Lab;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class P01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double [] input = Arrays.stream(scanner.nextLine().split("\\s+")).mapToDouble(Double::parseDouble)
                .toArray();
        Map<Double, Integer> inputMap = new TreeMap<>();
        for (double element : input) {
            if (!inputMap.containsKey(element)){
                inputMap.put(element,0);
            }
                inputMap.put(element,inputMap.get(element)+1);

        }
        for (Map.Entry<Double, Integer> entry : inputMap.entrySet()) {
            DecimalFormat df = new DecimalFormat("#.#######");
            System.out.printf("%s -> %d%n", df.format(entry.getKey()), entry.getValue());
        }


    }

}
