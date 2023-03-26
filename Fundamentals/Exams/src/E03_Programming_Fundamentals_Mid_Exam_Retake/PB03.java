package E03_Programming_Fundamentals_Mid_Exam_Retake;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PB03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Integer> mileageMap = new LinkedHashMap<>();
        Map<String, Integer> fuelMap = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("\\|");
            String car = input[0];
            int mileage = Integer.parseInt(input[1]);
            int fuel = Integer.parseInt(input[2]);

            if (!mileageMap.containsKey(car)) {
                mileageMap.put(car, mileage);
            }
            if (!fuelMap.containsKey(car)) {
                fuelMap.put(car, fuel);
            }
        }

        String commands = "";

        while (!"Stop".equals(commands = scanner.nextLine())) {
            String[] commandsArr = commands.split(" : ");
            String car = commandsArr[1];
            int availableFuel = fuelMap.get(car);
            int mileage = mileageMap.get(car);

            if (commands.contains("Drive")) {
                int distance = Integer.parseInt(commandsArr[2]);
                int fuelRequired = Integer.parseInt(commandsArr[3]);
                if (availableFuel >= fuelRequired) {
                    availableFuel -= fuelRequired;
                    fuelMap.replace(car, availableFuel);
                    mileage += distance;
                    mileageMap.replace(car,mileage);
                    System.out.printf("%s driven for %d kilometers. %d liters of fuel consumed.%n"
                            , car, distance, fuelRequired);

                    if (mileage >= 100000) {
                        mileageMap.remove(car);
                        fuelMap.remove(car);
                        System.out.println("Time to sell the " + car+"!");
                    }


                } else {
                    System.out.println("Not enough fuel to make that ride");
                }

            } else if (commands.contains("Refuel")) {
                int refuelAmount = Integer.parseInt(commandsArr[2]);
                availableFuel += refuelAmount; //74+40 = 114 - 75
                if (availableFuel > 75) {
                    availableFuel = 75;
                    refuelAmount = 75- fuelMap.get(car);
                }
                fuelMap.replace(car,availableFuel);
                System.out.printf("%s refueled with %d liters%n",car,refuelAmount);
            } else if (commands.contains("Revert")) {
                int revertedDistance = Integer.parseInt(commandsArr[2]);
                mileage -= revertedDistance;
                if (mileage<10000){
                    mileageMap.replace(car,10000);
                } else {
                    mileageMap.replace(car,mileage);
                    System.out.printf("%s mileage decreased by %d kilometers%n", car, revertedDistance);
                }

            }
        }

        for (Map.Entry<String, Integer> element : mileageMap.entrySet()) {
            String car = element.getKey();
            int mileage = element.getValue();
            int fuel = fuelMap.get(car);

            System.out.printf("%s -> Mileage: %d kms, Fuel in the tank: %d lt.%n", car, mileage,fuel);
        }

    }
}


