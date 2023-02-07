package Exercises;

import java.util.Scanner;

public class PB10_Treasure_Hunt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] chestInventory = scanner.nextLine().split("\\|");
        String commandString = "";


        while (!"Yohoho!".equals(commandString = scanner.nextLine())) {
            String[] commandArray = commandString.split(" ");
            switch (commandArray[0]) {
                case "Loot":
                    for (int i = 1; i <=commandArray.length-1 ; i++) {
                        boolean isNew = false;
                        for (int j = 0; j <= chestInventory.length-1 ; j++) {
                            if (commandArray[i].equals(chestInventory[j]) ){
                                break;
                            }
                            if ( j== chestInventory.length-1 ){
                                isNew = true;
                            }
                        }
                        if (isNew){
                            String[] increaseChest =new String [chestInventory.length+1];
                            increaseChest[0] = commandArray[i];

                            for (int j = 0; j <= chestInventory.length-1 ; j++) {
                                increaseChest[j+1] = chestInventory[j];
                            }
                            chestInventory = increaseChest;
                        }
                    }
                        break;
                        case "Drop":

                            int actionNumber = Integer.parseInt(commandArray[1]);
                            if (actionNumber<0){
                                continue;
                            }
                            String tempElementToMove = chestInventory[actionNumber];
                            for (int index = actionNumber; index < chestInventory.length-1 ; index++) {
                                chestInventory[index] = chestInventory[index+1];
                            }
                            chestInventory[chestInventory.length-1] = tempElementToMove;

                            break;
                        case "Steal ":
                            break;
                    }
            }

        for (String element: chestInventory) {
            System.out.print(element+" ");

        }

        }

    }
