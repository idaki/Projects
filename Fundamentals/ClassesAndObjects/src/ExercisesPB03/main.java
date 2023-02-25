package ExercisesPB03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nPeople = Integer.parseInt(scanner.nextLine());
        List<Person> peopleList = new ArrayList<>();
        for (int i = 0; i < nPeople; i++) {
           String[] input = scanner.nextLine().split(" ");
           String name = input[0];
           int age = Integer.parseInt(input[1]);
           Person currentPerson = new Person(name, age);
           if (age> 30){
               peopleList.add(currentPerson);
           }
        }
        for (Person element: peopleList) {
            System.out.println(element.getName()+" - "+element.getAge());

        }


    }
}
