package Exercises;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p04_SoftUniParking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCommands = Integer.parseInt(scanner.nextLine());
        Map<String, String> registryMap = new LinkedHashMap<>();

        for (int i = 0; i < numCommands; i++) {
            String[] inputArr = scanner.nextLine().split(" ");
            String command = inputArr[0];
            String name = inputArr[1];


            if ("register".contains(command) && !registryMap.containsKey(name)) {
                String licencePlate = inputArr[2];
                registryMap.put(name, licencePlate);
                System.out.printf("%s registered %s successfully%n", name, licencePlate);
            } else if ("register".contains(command)) {
                String licencePlate = inputArr[2];
                System.out.printf("ERROR: already registered with plate number %s%n", licencePlate);
            } else if ("unregister".contains(command) && registryMap.containsKey(name)) {
                System.out.printf("%s unregistered successfully%n", name);
                registryMap.remove(name);
            } else if ("unregister".contains(command)) {
                System.out.printf("ERROR: user %s not found%n", name);
            }
        }
        registryMap.forEach((key, value) -> System.out.println( key + " => "+ value ));
    }
}
