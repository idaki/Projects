package Exercises;

import java.util.*;

public class p09_ForceBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = "";
        Map<String, List> forceMap = new LinkedHashMap<>();
        while (!"Lumpawaroo".equals(input = scanner.nextLine())) {
            boolean isUnique = false;

            if (input.contains("|")) {
                String[] inputArr = input.split("\\|");
                String forceSide = inputArr[0];
                String forceUser = inputArr[1];

                for (Map.Entry<String, List> entry : forceMap.entrySet()) {
                    if (entry.getValue().contains(forceUser)) {
                        isUnique = true;
                    }
                }
                if (!forceMap.containsKey(forceUser) && !isUnique){
                    forceMap.put(forceSide,new ArrayList<>());
                    forceMap.get(forceSide).add(forceUser);
                } else if (!isUnique){
                    forceMap.get(forceSide).add(forceUser);
                }
            } else{
                String[] inputArr = input.split(" -> ");
                String forceSide = inputArr[0];
                String forceUser = inputArr[1];

                for (Map.Entry<String, List> entry : forceMap.entrySet()) {
                    if (entry.getValue().contains(forceUser)) {
                        isUnique = true;
                    }
                }
                if (!forceMap.containsKey(forceUser) && !isUnique){
                    forceMap.put(forceSide,new ArrayList<>());
                    forceMap.get(forceSide).add(forceUser);
                } else if (!isUnique){
                    forceMap.get(forceSide).add(forceUser);
                }
            }


            }


        }
    }
