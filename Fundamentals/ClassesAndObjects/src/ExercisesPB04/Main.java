package ExercisesPB04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            String firstName = input[0];
            String secondName = input[1];
            double grade = Double.parseDouble(input[2]);
            Student currentStudent = new Student(firstName, secondName, grade);
            studentList.add(currentStudent);
        }
        studentList.sort(Comparator.comparing(Student::getGrade).reversed());

        for (Student element: studentList) {
            System.out.println(element.toString());
        }

    }
}
