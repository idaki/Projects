package Exercises;

import com.sun.jdi.Value;

import java.util.*;

public class p06StudentAcademy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, List> studentMap = new LinkedHashMap<>();
        for (int i = 0; i < n ; i++) {
            String name = scanner.nextLine();
            double grade = Double.parseDouble(scanner.nextLine());

            if (!studentMap.containsKey(name)){
                studentMap.put(name, new ArrayList<>());
            }
            studentMap.get(name).add(grade);
        }

        for (Map.Entry<String, List> entry :studentMap.entrySet()) {
            double averageGrade = getAverageGrade(entry.getValue());
            if (averageGrade >=4.50){
            System.out.printf("%s -> %.2f%n",entry.getKey(), averageGrade);
            }
        }

    }

    private static double getAverageGrade(List <Double> grades) {

        double sum =0, result =0;
        for (double element : grades) {
            sum +=element;
        }
        result = sum / grades.size();

     return result;
    }
}
