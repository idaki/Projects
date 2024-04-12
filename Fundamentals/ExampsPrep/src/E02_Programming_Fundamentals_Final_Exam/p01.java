package E02_Programming_Fundamentals_Final_Exam;

import java.util.Scanner;

public class p01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stops = scanner.nextLine();
        String input = "";
        StringBuilder print = new StringBuilder(stops);
        while (!"Travel".equals(input = scanner.nextLine())) {
            String[] commandArr = input.split(":");

            if (input.contains("Add Stop")) {
                int index = Integer.parseInt(commandArr[1]);
                String newStop = commandArr[2];
                int indexStart = stops.lastIndexOf(":");
                int indexEnd = stops.length() - 1;
                char currentChar = stops.charAt(index);
                if (index >= 0 && index <= indexEnd) {

                    print.insert(index, newStop);

                }
            } else if (input.contains("Remove Stop")) {
                int indexStart = Integer.parseInt(commandArr[1]);
                int indexEnd = Integer.parseInt(commandArr[2]);
                if (indexStart >= 0 && indexEnd <= print.length() - 1) {
                    print.delete(indexStart, indexEnd + 1);
                }
            } else if (input.contains("Switch")) {
                String oldString = commandArr[1];
                String newString = commandArr[2];

                if (stops.contains(oldString) && print.toString().contains(oldString)) {


                    int indexStart = print.indexOf(oldString);
                    int indexLast = print.lastIndexOf(oldString);
                    String result = print.toString().replace(oldString, newString);

                    print.delete(0, print.length());
                    print.append(result);


                }

            }
            System.out.println(print);
        }
        System.out.println("Ready for world tour! Planned stops: "+print);


    }
}
