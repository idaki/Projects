package Exercises;

import java.util.*;

public class p09_ForceBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = "";
        Map<String, List> forceMap = new LinkedHashMap<>();
        while (!"Lumpawaroo".equals(input = scanner.nextLine())) {
            String[] inputArr = getInputData(input);
            String forceSide = inputArr[0];
            String forceUser = inputArr[1];

            if (input.contains("|")) {
                if (!forceMap.containsKey(forceSide)&& !(isExisting(forceMap, forceUser))) {
                    forceMap.put(forceSide, new ArrayList<>());
                    forceMap.get(forceSide).add(forceUser);
                } else if (!isExisting(forceMap, forceUser)) {
                    forceMap.get(forceSide).add(forceUser);
                }
            }else{
                if (isExisting(forceMap, forceUser)) {
                    updateForeSide(forceMap, forceSide, forceUser);

                } else if(!isExisting(forceMap, forceUser)&& forceMap.containsKey(forceSide)){
                    forceMap.get(forceSide).add(forceUser);
                }else{
                    forceMap.put(forceSide, new ArrayList<>());
                    forceMap.get(forceSide).add(forceUser);
                }

                System.out.println(forceUser+" joins the " +forceSide + " side!");
            }

        }
        forceMap.entrySet().
                forEach(entry->{
                    System.out.println("Side: "+entry.getKey()+", Members: "+ entry.getValue().size());
                    entry.getValue().forEach(name-> System.out.println("! "+ name));
                });
    }

    private static void updateForeSide(Map<String, List> forceMap, String forceSide, String forceUser) {
        if (forceMap.containsKey(forceSide)) {
            for (Map.Entry<String, List> entry : forceMap.entrySet()) {
                String force = entry.getKey();
                List<String> userList = entry.getValue();
                if (userList.contains(forceUser)) {
                    userList.remove(forceUser);
                    if (userList.size() == 0) {
                        forceMap.remove(force);
                    }
                    forceMap.get(forceSide).add(forceUser);
                }
            }
        }else{
            forceMap.put(forceSide, new ArrayList<>());
            forceMap.get(forceSide).add(forceUser);
        }
    }
    private static String[] getInputData(String input) {
        if (input.contains("|")) { //Light | Peter
            String[] inputArrTemp = input.split(" \\| ");
            String[] inputArr = new String[2];
            inputArr[0] = inputArrTemp[0]; //force
            inputArr[1] = inputArrTemp[1]; //name
            return inputArr;
        } else { //DCay -> Lighter
            String[] inputArrTemp = input.split(" -> ");
            String[] inputArr = new String[2];
            inputArr[0] = inputArrTemp[1]; //force
            inputArr[1] = inputArrTemp[0]; //name
            return inputArr;
        }
    }

    public static boolean isExisting(Map<String, List> forceMap, String forceUser) {
        boolean isUnique = false;
        for (Map.Entry<String, List> entry : forceMap.entrySet()) {
            if (entry.getValue().contains(forceUser)) {
                isUnique = true;
            }
        }
        return isUnique;
    }
}

