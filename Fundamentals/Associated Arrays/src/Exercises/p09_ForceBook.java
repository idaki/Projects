package Exercises;

import java.util.*;

public class p09_ForceBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = "";
        Map<String, List> forceMap = new LinkedHashMap<>();
        while (!"Lumpawaroo".equals(input = scanner.nextLine())) {
            boolean isUnique = false;
            boolean isExisting = false;
            gy
            if (input.contains("|")) {
                String[] inputArr = input.split(" \\| ");
                String forceSide = inputArr[0];
                String forceUser = inputArr[1];

                for (Map.Entry<String, List> entry : forceMap.entrySet()) {
                    if (entry.getValue().contains(forceUser)) {
                        isUnique = true;
                    }
                }
                if (!forceMap.containsKey(forceUser) && !isUnique) {
                    forceMap.put(forceSide, new ArrayList<>());
                    forceMap.get(forceSide).add(forceUser);
                } else if (!isUnique) {
                    forceMap.get(forceSide).add(forceUser);
                }
            } else {
                String[] inputArr = input.split(" -> ");
                String forceUser = inputArr[0];
                String forceSide = inputArr[1];

                for (Map.Entry<String, List> entry : forceMap.entrySet()) {
                    if (entry.getValue().contains(forceUser)) {
                        isExisting = true;
                    }
                }

                if (isExisting & forceMap.containsKey(forceSide)) {
                    for (Map.Entry <String,List> entry : forceMap.entrySet()) {
                        String force = entry.getKey();
                        List<String> userList = entry.getValue();
                        if  (userList.contains(forceUser)){
                            userList.remove(forceUser);
                            if (userList.size() == 0){
                                forceMap.remove(force);
                            }
                            forceMap.get(forceSide).add(forceUser);
                        }

                    }
                } else if (!isExisting) {
                   forceMap.get(forceSide).add(forceUser);

                } else if (!forceMap.containsKey(forceSide) & !isExisting){
                    forceMap.put(forceSide, new ArrayList<>());
                    forceMap.get(forceSide).add(forceUser);
                }
                System.out.println(forceUser+" joins the " +forceSide + " side!");
            }
        }
        forceMap.entrySet().
                forEach(entry->{
                    System.out.println("Side: "+entry.getKey()+", Members: "+ entry.getValue().size());
                    entry.getValue().forEach(forceUser-> System.out.println("! "+ forceUser));
                });
    }
}

