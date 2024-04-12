package E05;

import java.util.*;

public class p03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String initialInput = "";
        Map<String, List<Integer>> cityMap = new LinkedHashMap<>();

        while (!"Sail".equals(initialInput = scanner.nextLine())) {
            String[] inputArr = initialInput.split("\\|\\|");
            String city = inputArr[0];
            int population = Integer.parseInt(inputArr[1]);
            int gold = Integer.parseInt(inputArr[2]);
            if (!cityMap.containsKey(city)) {
                cityMap.put(city, new ArrayList<>());
                cityMap.get(city).add(population);
                cityMap.get(city).add(gold);
            } else {
                int oldPopulation = (int) cityMap.get(city).get(0);
                int newPopulation = oldPopulation + population;
                int oldGold = (int) cityMap.get(city).get(1);
                int newGold = oldGold + gold;
                cityMap.get(city).set(0, newPopulation);
                cityMap.get(city).set(1, newGold);


            }
        }
        String events = "";

        while (!"End".equals(events = scanner.nextLine())) {
            String[] eventArr = events.split("=>");
            String city = eventArr[1];
            if (events.contains("Plunder")) {
                int people = Integer.parseInt(eventArr[2]);
                int gold = Integer.parseInt(eventArr[3]);

                int population = cityMap.get(city).get(0);
                population -= people;
                int totalGold = cityMap.get(city).get(1);
                totalGold -= gold;

                System.out.printf("%s plundered! %d gold stolen, %d citizens killed.%n", city, gold, people);

                cityMap.get(city).set(0, population);
                cityMap.get(city).set(1, totalGold);

                if (population == 0 || totalGold == 0) {
                    cityMap.remove(city);
                    System.out.println(city + " has been wiped off the map!");
                }
            } else if (events.contains("Prosper")) {
                int people = Integer.parseInt(eventArr[2]);
                int totalGold = cityMap.get(city).get(1);
                int gold = Integer.parseInt(eventArr[2]);
                if (gold <= 0) {
                    System.out.println("Gold added cannot be a negative number!");
                    continue;
                }
                totalGold += gold;
                cityMap.get(city).set(1, totalGold);
                System.out.printf("%d gold added to the city treasury. %s now has %d gold.%n", gold, city, totalGold);
            }
        }

        if (cityMap.isEmpty()) {
            System.out.println("Ahoy, Captain! All targets have been plundered and destroyed!");
        } else {
            System.out.printf("Ahoy, Captain! There are %d wealthy settlements to go to:%n", cityMap.size());
            for (Map.Entry<String, List<Integer>> entry : cityMap.entrySet()) {
                String city = entry.getKey();
                int population = cityMap.get(city).get(0);
                int gold = cityMap.get(city).get(1);

                System.out.printf("%s -> Population: %d citizens, Gold: %d kg%n", city, population, gold);
            }
        }
    }
}
