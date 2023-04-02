package E04;

import java.util.*;

public class p03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, List<Integer>> heroPointsMap = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            String[] inputArr = input.split(" ");
            String name = inputArr[0];
            int hitPoints = Integer.parseInt(inputArr[1]);
            int manaPoints = Integer.parseInt(inputArr[2]);
            heroPointsMap.put(name, new ArrayList<>());
            heroPointsMap.get(name).add(hitPoints);
            heroPointsMap.get(name).add(manaPoints);

        }


        String command = "";

        while (!"End".equals(command = scanner.nextLine())) {
            String[] commandArr = command.split(" - ");
            String name = commandArr[1];

            if(!heroPointsMap.containsKey(name)){
                continue;
            }

            int hitPointsAvailable = heroPointsMap.get(name).get(0);
            int manaPointsAvailable = heroPointsMap.get(name).get(1);
            if (command.contains("CastSpell")) {

//CastSpell – {hero name} – {MP needed} – {spell name}"

                int manaPointsNeeded = Integer.parseInt(commandArr[2]);
                String spell = commandArr[3];

                if (manaPointsAvailable >= manaPointsNeeded) {
                    manaPointsAvailable -= manaPointsNeeded;
                    heroPointsMap.get(name).set(1, manaPointsAvailable);

                    System.out.printf("%s has successfully cast %s and now has %d MP!%n"
                            , name, spell, manaPointsAvailable);
                } else {
                    System.out.printf("%s does not have enough MP to cast %s!%n", name, spell);

                }
            } else if (command.contains("TakeDamage")) {
                //TakeDamage – {hero name} – {damage} – {attacker}"

                int hitPointsNeeded = Integer.parseInt(commandArr[2]);

                String attacker = commandArr[3];
                hitPointsAvailable -= hitPointsNeeded;

                if (hitPointsAvailable > 0) {
                    System.out.printf("%s was hit for %d HP by %s and now has %d HP left!%n"
                            , name, hitPointsNeeded, attacker, hitPointsAvailable);
                    heroPointsMap.get(name).set(0, hitPointsAvailable);

                } else {
                    System.out.printf("%s has been killed by %s!%n", name, attacker);
                    heroPointsMap.remove(name);
                }

            } else if (command.contains("Recharge")) {
//"Recharge – {hero name} – {amount}"

                int addManaPoints = Integer.parseInt(commandArr[2]);
                manaPointsAvailable += addManaPoints;

                if (manaPointsAvailable > 200) {
                    addManaPoints = 200 - heroPointsMap.get(name).get(1);
                    manaPointsAvailable = 200;
                }

                heroPointsMap.get(name).set(1,manaPointsAvailable);
                System.out.printf("%s recharged for %d MP!%n", name, addManaPoints);

            } else if (command.contains("Heal")) {
                //"Heal – {hero name} – {amount}"

                int addHitPoints = Integer.parseInt(commandArr[2]);
                hitPointsAvailable += addHitPoints;

                if (hitPointsAvailable > 100) {
                    addHitPoints = 100 - heroPointsMap.get(name).get(0);
                    hitPointsAvailable = 100;
                }
                heroPointsMap.get(name).set(0,hitPointsAvailable);
                System.out.printf("%s healed for %d HP!%n", name, addHitPoints);
            }

        }

        for (Map.Entry<String, List<Integer>> entry : heroPointsMap.entrySet()) {

            String name = entry.getKey();
            int hitPoints = heroPointsMap.get(name).get(0);
            int manaPoints = heroPointsMap.get(name).get(1);


            System.out.println(name);
            System.out.println("  HP: " + hitPoints);
            System.out.println("  MP: " + manaPoints);
        }

    }

}
