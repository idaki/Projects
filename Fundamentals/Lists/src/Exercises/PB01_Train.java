package Exercises;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PB01_Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numsWagons = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());
        int capacityWagon = Integer.parseInt(scanner.nextLine());
        String command = "";
        while (!"end".equals(command = scanner.nextLine())) {

        if (command.contains("Add")){
           int wagonData = Integer.parseInt(command.split(" ")[1]);
            numsWagons.add(wagonData);
        }else{
          int  numPeople = Integer.parseInt(command);
            for (int i = 0; i <= numsWagons.size()-1 ; i++) {
                if (numsWagons.get(i)+numPeople <= capacityWagon ){
                    numsWagons.set(i,numsWagons.get(i)+numPeople);
                    break;
                }
            }
        }
        }
        System.out.println(numsWagons.toString().replaceAll("[\\[\\],]",""));

    }
}
