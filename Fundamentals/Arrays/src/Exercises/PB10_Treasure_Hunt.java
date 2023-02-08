package Exercises;

import java.util.Scanner;

public class PB10_Treasure_Hunt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] chestInventory = scanner.nextLine().split("\\|");
        String commandString = "", stolenItemsPrint = "";


        while (!"Yohoho!".equals(commandString = scanner.nextLine())) {
            String[] commandArray = commandString.split(" ");
            switch (commandArray[0]) {
                case "Loot":
                    for (int i = 1; i <= commandArray.length - 1; i++) {
                        boolean isNew = false;
                        for (int j = 0; j <= chestInventory.length - 1; j++) {
                            if (commandArray[i].equals(chestInventory[j])) {
                                break;
                            }
                            if (j == chestInventory.length - 1) {
                                isNew = true;
                            }
                        }
                        if (isNew) {
                            String[] increaseChest = new String[chestInventory.length + 1];
                            increaseChest[0] = commandArray[i];

                            for (int j = 0; j <= chestInventory.length - 1; j++) {
                                increaseChest[j + 1] = chestInventory[j];
                            }
                            chestInventory = increaseChest;
                        }
                    }
                    break;
                case "Drop":

                    int actionNumber = Integer.parseInt(commandArray[1]);
                    if (actionNumber < 0 || actionNumber> chestInventory.length) {
                        continue;
                    }

                    String tempElementToMove = chestInventory[actionNumber];
                    for (int index = actionNumber; index < chestInventory.length - 1; index++) {
                        chestInventory[index] = chestInventory[index + 1];
                    }
                    chestInventory[chestInventory.length - 1] = tempElementToMove;

                    break;
                case "Steal":
                    int stolenItemsNumber = Integer.parseInt(commandArray[1]);

                    if (stolenItemsNumber > chestInventory.length){
                        stolenItemsNumber = chestInventory.length;
                    }
                    int counter = stolenItemsNumber;

                    while (counter > 0) {

                        String[] decreaseChest = new String[chestInventory.length - 1];

                        if (counter == stolenItemsNumber) {
                            stolenItemsPrint = String.format("%s", chestInventory[chestInventory.length - 1]);
                        } else {
                            stolenItemsPrint = String.format("%s, %s", chestInventory[chestInventory.length - 1]
                                    , stolenItemsPrint);
                        }

                        counter--;

                        for (int index = 0; index < chestInventory.length - 1; index++) {
                            decreaseChest[index] = chestInventory[index];
                        }
                        chestInventory = decreaseChest;

                        if (chestInventory.length == 0) {
                            break;
                        }
                    }
                    break;
            }
        }

        int sumLength = 0;
        for (String element : chestInventory) {
            sumLength += element.length();
        }
       if (stolenItemsPrint.length()>0){
        System.out.println(stolenItemsPrint);
       }

        if (chestInventory.length > 0) {
            double averageTreasureGain = sumLength * 1.0 / chestInventory.length;
            System.out.printf("Average treasure gain: %.2f pirate credits.", averageTreasureGain);
        } else  {
            System.out.println("Failed treasure hunt.");
        }
    }
}
