package Lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PB05_Students {

    static class Student{
        String firstName;
        String lastName;
        String age;
        String city;

        public Student(String firstNameInput, String lastNameInput, String ageInput, String cityInput){
            this.firstName = firstNameInput;
            this.lastName = lastNameInput;
            this.age= ageInput;
            this.city = cityInput;
        }

        public String getFirstName(){
            return this.firstName;
        }

        public String getLastName(){
            return this.lastName;
        }
        public String getAge(){
            return this.age;
        }
        public String getCity(){
            return this.city;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        List<Student> studentsList = new ArrayList<>();
        while (!"end".equals(input = scanner.nextLine())){

            String[] currentInfo = input.split(" ");
            String firstNameInput = currentInfo[0];
            String lastNameInput = currentInfo[1];
            String ageInput = currentInfo[2];
            String cityInput = currentInfo[3];

            Student currentStudent = new Student(firstNameInput, lastNameInput, ageInput, cityInput);
            studentsList.add(currentStudent);
        }

        String command = scanner.nextLine();

        for (Student student : studentsList) {
            if (command.equals(student.getCity())){
                String firstName =student.getFirstName();
                String lastName =student.getLastName();
                String age =student.getAge();
                System.out.printf("%s %s is %s years old%n",firstName, lastName, age);
            }
        }

    }
}
