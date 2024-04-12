package Exercise06;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input ="";
        List<Profile> peopleList = new ArrayList<>();

        while(!"End".equals(input = scanner.nextLine())){
            String[] data = input.split("\\s+");
            String name = data[0];
            String id = data[1];
            int age = Integer.parseInt(data[2]);
            Profile person = new Profile(name, id, age);
            peopleList.add(person);
        }
        peopleList.sort(Comparator.comparing(Profile::getAge));
        for (Profile element: peopleList) {
            System.out.println(element.toString());
        }
    }
}
