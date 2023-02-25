package E05_Programming_Fundamentals_Mid_Exam;

import java.util.Arrays;
import java.util.Scanner;

public class PB02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputCommand = (scanner.nextLine().split("\\|"));
        int health = 100, bitcoin = 0;

        for (int i = 0; i <=inputCommand.length-1 ; i++) {
            String[] action = inputCommand[i].split("\\s+");
            String command = action[0];
            int token = Integer.parseInt(action[1]);

            if (  "potion".equals(command)){
                int previousHealth = health;
                health +=token;
                if (health>100){
                    token =100-previousHealth;
                    health = 100;
                }
                System.out.printf("You healed for %d hp.%n", token);
                System.out.printf("Current health: %d hp.%n", health);

            } else if ("chest".equals(command)) {
                bitcoin+= token;
                System.out.printf("You found %d bitcoins.%n", token);
            } else{
                health -= token;
                if (health>0){
                    System.out.printf("You slayed %s.%n",command);
                }else {
                    System.out.printf("You died! Killed by %s.%n",command);
                    System.out.printf("Best room: %d",i+1);
                    return;
                }
            }
        }
        System.out.println("You've made it!");
        System.out.println("Bitcoins: " + bitcoin);
        System.out.println("Health: " + health);

    }
}
