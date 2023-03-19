package MoreExercises;

import java.util.*;
import java.util.stream.Collectors;

public class p01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputPassword = "";
        Map<String, String> passwordMap = new LinkedHashMap<>();
        while(!"end of contests".equals(inputPassword = scanner.nextLine())){
            String[] passwordArr = inputPassword.split(":");
            String courseName = passwordArr[0];
            String coursePassword = passwordArr[1];
           passwordMap.put(courseName,coursePassword);
        }
        String studentDataInput = "";
        Map<String, List> studentMap = new LinkedHashMap<>();

        while(!"end of submissions".equals(studentDataInput = scanner.nextLine())){
            String[] studentDataArr = studentDataInput.split("=>");
            String courseName= studentDataArr[0];
            String coursePassword = studentDataArr[1];
            String studentName = studentDataArr[2];
            int points = Integer.parseInt(studentDataArr[3]);

            if (passwordMap.containsKey(courseName)
                    && passwordMap.get(courseName).equals(coursePassword) ){

                if (!studentMap.containsKey(studentName)){

                }


            }





        }



    }
}
