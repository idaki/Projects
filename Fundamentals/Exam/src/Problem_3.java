import java.util.*;

public class Problem_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> guestMealMap = new LinkedHashMap<>();

        String guestInfoInput = "";
        int unlikedMealsCount = 0;

        while (!"Stop".equals(guestInfoInput = scanner.nextLine())) {
            String[] guestInfoArr = guestInfoInput.split("-");
            String name = guestInfoArr[1];
            String meal = guestInfoArr[2];

            if (guestInfoInput.contains("Like")) {
                if (!guestMealMap.containsKey(name)) {
                    guestMealMap.put(name, new ArrayList<>());
                    guestMealMap.get(name).add(meal);
                } else {
                    for (Map.Entry<String, List<String>> entry : guestMealMap.entrySet()) {
                        String currentNameCheck = entry.getKey();
                        if (currentNameCheck.equals(name)) {
                            if (!entry.getValue().contains(meal)) {
                                guestMealMap.get(name).add(meal);
                            }
                        }
                    }
                }

            } else if (guestInfoInput.contains("Dislike")) {

                if (!guestMealMap.containsKey(name)){
                    System.out.println(name + " is not at the party.");
                } else if (!guestMealMap.get(name).contains(meal)) {
                    System.out.printf("%s doesn't have the %s in his/her collection.%n", name, meal);
                } else{
                    unlikedMealsCount++;
                    int indexToDelete = guestMealMap.get(name).indexOf(meal);
                    guestMealMap.get(name).remove(indexToDelete);
                    System.out.printf("%s doesn't like the %s.%n", name, meal);
                }
            }
        }
        for (Map.Entry<String, List<String>> entry: guestMealMap.entrySet()) {
            String name = entry.getKey();
            List<String> meals = new ArrayList<>(entry.getValue());
            System.out.println(name+": "+meals.toString().replaceAll("[\\[\\]]",""));
        }
        System.out.println("Unliked meals: "+ unlikedMealsCount);


    }
}
