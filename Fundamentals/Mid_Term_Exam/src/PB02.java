import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PB02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> nameList = Arrays.stream(scanner.nextLine().replaceAll(",","").split("\\s+"))
                .collect(Collectors.toList());

        String commandLine = "";

        int countBlacklisted = 0 , countLostNames = 0;

        while (!"Report".equals(commandLine = scanner.nextLine())) {
            List <String> command = Arrays.stream(commandLine.split("\\s+"))
                    .collect(Collectors.toList());

            if (commandLine.contains("Blacklist")) {
                if (nameList.contains(command.get(1))) {
                    int index = nameList.indexOf(command.get(1));
                    nameList.set(index, "Blacklisted");
                    System.out.println(command.get(1)+" was blacklisted.");
                    countBlacklisted++;
                } else{
                    System.out.println(command.get(1)+" was not found.");
                }
            } else if (commandLine.contains("Error")) {
                    int index = Integer.parseInt(command.get(1));
                       if  (index>=0 && index<= nameList.size()-1
                               && !nameList.get(index).equals("Blacklisted")
                               && !nameList.get(index).equals("Lost"))  {
                           System.out.println(nameList.get(index)+" was lost due to an error.");
                        nameList.set(index,"Lost");
                        countLostNames++;
                       }
            } else if (commandLine.contains("Change")) {
                        int index = Integer.parseInt(command.get(1));
                        if  (index>=0 && index<= nameList.size()-1){
                            System.out.printf("%s changed his username to %s.%n", nameList.get(index),command.get(2));
                            nameList.set(index,command.get(2));

                        }
            }

        }
        System.out.println("Blacklisted names: "+countBlacklisted);
        System.out.println("Lost names: "+ countLostNames);
        System.out.println(nameList.toString().replaceAll("[\\[\\],]",""));


    }
}
