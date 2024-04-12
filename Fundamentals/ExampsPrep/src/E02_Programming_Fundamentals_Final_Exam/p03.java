package E02_Programming_Fundamentals_Final_Exam;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        if (n <= 0) {
            return;
        }
        Map<String, String> plantRarityMap = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] currentPlantInput = scanner.nextLine().split("<->");
            String name = currentPlantInput[0];
            String rarity = currentPlantInput[1];
            if (!plantRarityMap.containsKey(name)) {
                plantRarityMap.put(name, rarity);
            } else {
                plantRarityMap.replace(name, rarity);
            }
        }
        System.out.println();
        String commandsInput = "";
        String regex = "\\: (?<name>[A-Za-z]+)( - )?(?<rating>[0-9]+)?";
        Pattern pattern = Pattern.compile(regex);

        Map<String, List> plantRatingMap = new LinkedHashMap<>();
        while (!"Exhibition".equals(commandsInput = scanner.nextLine())) {
            Matcher matcher = pattern.matcher(commandsInput);
            double rating = 0.0;
            String name = "";
            String rarity = "";
            while (matcher.find()) {
                name = matcher.group("name");

                if (plantRarityMap.containsKey(name)) {
                    if (commandsInput.contains("Rate")) {

                        if (matcher.group("rating")== null){
                            continue;
                        }
                        rating = Double.parseDouble(matcher.group("rating"));
                        if (!plantRatingMap.containsKey(name)) {
                            plantRatingMap.put(name, new ArrayList<>());
                        }
                        plantRatingMap.get(name).add(rating);
                    } else if ((commandsInput.contains("Update"))) {
                        rarity = matcher.group("rating");
                        plantRarityMap.replace(name, rarity);
                    } else if ((commandsInput.contains("Reset"))) {
                        plantRatingMap.put(name, new ArrayList<>());
                        plantRatingMap.get(name).add(0.0);
                    }
                } else {
                    System.out.println("error");
                }
            }

        }
        System.out.println("Plants for the exhibition:");
        for (Map.Entry<String, String> entry : plantRarityMap.entrySet()) {
            String name = entry.getKey();
            String rarity = entry.getValue();

            if (!plantRatingMap.containsKey(name)){
                plantRatingMap.put(name, new ArrayList<>());
                plantRatingMap.get(name).add(0.0);
            }

            List<Double> currentList = new ArrayList<>(plantRatingMap.get(name));
            double ratingAvg = currentList.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            System.out.printf("- %s; Rarity: %s; Rating: %.2f%n", name, rarity, ratingAvg);
        }

    }
}