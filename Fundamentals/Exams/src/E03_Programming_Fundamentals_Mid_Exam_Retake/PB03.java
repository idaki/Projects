package E03_Programming_Fundamentals_Mid_Exam_Retake;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PB03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> targets = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toList());
        String command = "";
        while (!"End".equals(command = scanner.nextLine())) {
            List<String> commandList = Arrays.stream(command.split(" "))
                    .collect(Collectors.toList());
            if (commandList.contains("Shoot")) {

                int index = Integer.parseInt(commandList.get(1));
                int power = Integer.parseInt(commandList.get(2));

                if (index < 0 || index > targets.size() - 1) {
                    continue;
                }
                int newValue = targets.get(index) - power;
                targets.set(index, newValue);

                if (newValue <= 0) {
                    targets.remove(index);
                }

            } else if (commandList.contains("Add")) {
                int index = Integer.parseInt(commandList.get(1));
                int value = Integer.parseInt(commandList.get(2));
                if (index < 0 || index > targets.size() - 1) {
                    System.out.println("Invalid placement!");
                    continue;
                }
                targets.add(index, value);

            } else {
                int index = Integer.parseInt(commandList.get(1));
                int radius = Integer.parseInt(commandList.get(2));
                int counter = 0;
                if (index - radius < 0 || index+radius > targets.size() - 1) {
                    System.out.println("Strike missed!");
                    continue;
                }

                while (targets.size()-1>index){
                    targets.remove(index+1);
                    counter++;
                    if (counter == radius){
                        break;
                    }
                }
                counter =0;
                while (index-1>=0){
                    targets.remove(index-1);
                    index--;
                    counter++;
                    if (counter == radius){
                        break;
                    }
                }

                targets.remove(index);



            }

        }
        String print =(targets.toString().replace("[","").replace("]","")
                .replace(",",""));
        print= print.replaceAll(" ","\\|");
        System.out.println(print);
    }
}


