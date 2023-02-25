import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PB03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> phoneList = Arrays.stream(scanner.nextLine().replaceAll(",","").split("\\s+"))
                .collect(Collectors.toList());

        String commandLine = "";

        int countBlacklisted = 0 , countLostNames = 0;

        while (!"End".equals(commandLine = scanner.nextLine())) {


            List <String> command = Arrays.stream(commandLine.split(" - "))
                    .collect(Collectors.toList());

          if (command.contains("Add")){
              if (!phoneList.contains(command.get(1))){
                  phoneList.add(command.get(1));
              }
          } else if (command.contains("Remove")) {
              if (phoneList.contains(command.get(1))){
                  phoneList.remove(command.get(1));
              }

          }else if (command.contains("Bonus phone")) {
              List <String> subCommand = Arrays.stream(commandLine.split(" - "))
                      .collect(Collectors.toList());
             String[] phone = subCommand.get(1).split("\\:");
            String oldPhone = phone[0];
            String newPhone = phone [1];

              if (phoneList.contains(oldPhone)){
                  int index = phoneList.indexOf(oldPhone);
                  phoneList.add(index+1, newPhone);
              }

          }else if (command.contains("Last")) {
              if (phoneList.contains(command.get(1))){
                  int index = phoneList.indexOf(command.get(1));
                  phoneList.add(command.get(1));
                  phoneList.remove(index);
              }
          }

        }

        System.out.println(phoneList.toString().replaceAll("[\\[\\]]",""));

    }
}
