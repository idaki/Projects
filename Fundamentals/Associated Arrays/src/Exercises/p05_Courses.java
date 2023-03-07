package Exercises;

import java.util.*;

public class p05_Courses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = "";
        Map<String, List> coursesMap = new LinkedHashMap<>();
        while (!"end".equals(input = scanner.nextLine())) {
        String[] courseInputArr = input.split(" :");

        String courseName = courseInputArr[0];
        String studentName = courseInputArr[1];

        if (!coursesMap.containsKey(courseName)){
            coursesMap.put(courseName,new ArrayList<>());
        }
        coursesMap.get(courseName).add(studentName);
        }
        coursesMap.entrySet()
        .forEach(company-> {
                    System.out.println(company.getKey()+ ": " + company.getValue().size());
                    company.getValue().forEach(studentName -> System.out.println("-- "+studentName));
                } );


    }
}
