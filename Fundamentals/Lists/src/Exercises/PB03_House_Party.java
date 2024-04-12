package Exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PB03_House_Party {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numGuests = Integer.parseInt(scanner.nextLine());
        String guestInfo = "";
        List<String> guestList = new ArrayList<>();
        for (int i = 0; i < numGuests; i++) {
            guestInfo = scanner.nextLine();
            String name = guestInfo.split(" ")[0];

            if (guestInfo.contains("is going!")) {
                if (guestList.contains(name)) {
                    System.out.printf("%s is already in the list!%n", name);
                } else {
                    guestList.add(name);
                }
            } else if (guestList.contains(name)) {
                guestList.remove(name);
            } else {
                System.out.printf("%s is not in the list!%n", name);
            }
        }
        for (String element: guestList) {
            System.out.println(element);

        }
    }
}
