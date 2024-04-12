package Lab;

import java.util.Scanner;

public class PB01_Day_of_Week {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dayNumber = Integer.parseInt(scanner.nextLine());

        String[] day = {"Monday"
                , "Tuesday"
                , "Wednesday"
                , "Thursday"
                , "Friday"
                , "Saturday"
                , "Sunday"
        };

      if (dayNumber>0 &&dayNumber<=7){
          System.out.println(day[dayNumber]);
      }else{
          System.out.println("Invalid day!");
      }
        }
}
