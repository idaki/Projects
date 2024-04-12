package Exercises;

import java.util.*;

public class p08CompanyUsers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = "";
        Map<String, List> companyMap = new LinkedHashMap<>();
        while (!"End".equals(input = scanner.nextLine())) {
            String[] inputArr = input.split(" -> ");
            String company = inputArr[0];
            String employeeID = inputArr[1];

            if (!companyMap.containsKey(company)) {
                companyMap.put(company, new ArrayList<>());
            }
            if (!companyMap.get(company).contains(employeeID)) {
                companyMap.get(company).add(employeeID);
            }
        }
       /* for (Map.Entry<String, List> entry : companyMap.entrySet()) {
            System.out.println(entry.getKey());
            for (Object element : entry.getValue()) {
                System.out.println("-- " + element);
            }
        }*/

        companyMap.entrySet()
                .forEach(entry ->{
                    System.out.println(entry.getKey());
                    entry.getValue().forEach(employeeID-> System.out.println("-- "+ employeeID));
                });

    }
}
