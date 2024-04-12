package Exercises;

import java.util.*;
import java.util.stream.Collectors;

public class PB10_Treasure_HuntList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> chestInventory = Arrays.stream(scanner.nextLine()
                .split("\\|")).collect(Collectors.toList());
        String commandString = "", stolenItemsPrint = "";


        while (!"Yohoho!".equals(commandString = scanner.nextLine())) {
            String[] commandArray = commandString.split("\\s+");

            switch (commandArray[0]) {
                case "Loot":
                    for (int i = 1; i <= commandArray.length - 1; i++) {
                        if (!chestInventory.contains(commandArray[i])) {
                            chestInventory.add(0, commandArray[i]);
                        }
                    }

                    break;
                case "Drop":

                    int actionNumber = Integer.parseInt(commandArray[1]);
                    if (actionNumber >= 0 && actionNumber <= chestInventory.size() - 1) {
                        chestInventory.add(chestInventory.get(actionNumber));
                        chestInventory.remove(actionNumber);
                    }
                    break;
                case "Steal":
                    int stolenItemsNumber = Integer.parseInt(commandArray[1]);
                    List<String> printSolenItems = new ArrayList<>();
                    if (stolenItemsNumber<0){
                        continue;
                    }
                    for (int i = stolenItemsNumber; i > 0; i--) {
                        if (chestInventory.size()>0){
                        printSolenItems.add(chestInventory.get(chestInventory.size()-1));
                        chestInventory.remove(chestInventory.size()-1);
                        }else {
                            break;
                        }
                    }
                    Collections.reverse(printSolenItems);
                    System.out.println(String.join(", ",printSolenItems));
                    break;
            }
        }

        int sumLength = 0;
        for (String element : chestInventory) {
            sumLength += element.length();
        }


        if (chestInventory.size() > 0 && sumLength>0){
            double averageTreasureGain = sumLength * 1.0 / chestInventory.size();
            System.out.printf("Average treasure gain: %.2f pirate credits.", averageTreasureGain);
        } else {
            System.out.println("Failed treasure hunt.");
        }
    }
}
