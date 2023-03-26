package E04;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Integer> hitPointsMap = new LinkedHashMap<>();
        Map<String, Integer> manaPointsMap = new LinkedHashMap<>();

        consoleHeroesInput(scanner, n, hitPointsMap, manaPointsMap);

        String command = "";

        while (!"End".equals(command = scanner.nextLine())) {
            String[] commandArr = command.split("\\s+-\\s+");
            String name = commandArr[1];
            if(!hitPointsMap.containsKey(name)){
                continue;
            }

            if (command.contains("CastSpell")) {

//CastSpell – {hero name} – {MP needed} – {spell name}"

                int manaPointsNeeded = Integer.parseInt(commandArr[2]);
                String spell = commandArr[3];

                int manaPointsAvailable = manaPointsMap.get(name);

                if (manaPointsAvailable >= manaPointsNeeded) {
                    manaPointsAvailable -= manaPointsNeeded;
                    manaPointsMap.replace(name, manaPointsAvailable);
                    System.out.printf("%s has successfully cast %s and now has %d MP!%n"
                            , name, spell, manaPointsAvailable);
                } else {
                    System.out.printf("%s does not have enough MP to cast %s!%n", name, spell);
                    manaPointsMap.remove(name);
                    hitPointsMap.remove(name);
                }
            } else if (command.contains("TakeDamage")) {
                //TakeDamage – {hero name} – {damage} – {attacker}"
                name = commandArr[1];
                int hitPointsNeeded = Integer.parseInt(commandArr[2]);
                String attacker = commandArr[3];
                int hitPointsAvailable = hitPointsMap.get(name);

                hitPointsAvailable -= hitPointsNeeded;

                if (hitPointsAvailable > 0) {
                    System.out.printf("%s was hit for %d HP by %s and now has %d HP left!%n"
                            , name, hitPointsNeeded, attacker, hitPointsAvailable);
                    hitPointsMap.replace(name, hitPointsAvailable);
                } else {
                    System.out.printf("%s has been killed by %s!%n", name, attacker);
                    manaPointsMap.remove(name);
                    hitPointsMap.remove(name);
                }

            } else if (command.contains("Recharge")) {
//"Recharge – {hero name} – {amount}"

                name = commandArr[1];
                int addManaPoints = Integer.parseInt(commandArr[2]);
                int manaPointsAvailable = manaPointsMap.get(name);
                manaPointsAvailable += addManaPoints;

                if (manaPointsAvailable > 200) {
                    addManaPoints = 200 - manaPointsMap.get(name);
                    manaPointsAvailable = 200;
                }
                manaPointsMap.replace(name, manaPointsAvailable);
                System.out.printf("%s recharged for %d MP!%n", name, addManaPoints);

            } else if (command.contains("Heal")) {
                //"Heal – {hero name} – {amount}"
                name = commandArr[1];
                int addHitPoints = Integer.parseInt(commandArr[2]);
                int hitPointsAvailable = hitPointsMap.get(name);
                hitPointsAvailable += addHitPoints;

                if (hitPointsAvailable > 100) {
                    addHitPoints = 100 - hitPointsMap.get(name);
                    hitPointsAvailable = 100;
                }
                hitPointsMap.replace(name, hitPointsAvailable);
                System.out.printf("%s healed for %d HP!%n", name, addHitPoints);
            }

        }

        for (Map.Entry<String, Integer> entry : manaPointsMap.entrySet()) {
            String name = entry.getKey();
            int manaPoints = manaPointsMap.get(name);
            int hitPoints = hitPointsMap.get(name);

            System.out.println(name);
            System.out.println("  HP: " + hitPoints);
            System.out.println("  MP: " + manaPoints);
        }


    }

    private static void consoleHeroesInput(Scanner scanner, int n, Map<String, Integer> hitPointsMap, Map<String, Integer> manaPointsMap) {
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            String[] inputArr = input.split("\\s+");
            String name = inputArr[0];
            if (!hitPointsMap.containsKey(name)){
            int hitPoints = Integer.parseInt(inputArr[1]);
            int manaPoints = Integer.parseInt(inputArr[2]);
            hitPointsMap.put(name, hitPoints);
            manaPointsMap.put(name, manaPoints);
            }
        }
    }
}
