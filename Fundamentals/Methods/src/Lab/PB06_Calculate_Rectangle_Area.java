package Lab;

import java.util.Scanner;

public class PB06_Calculate_Rectangle_Area {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double sideASizeInput = Double.parseDouble(scanner.nextLine());
        double sideBSizeInput = Double.parseDouble(scanner.nextLine());

        System.out.printf("%.0f",(calculateTriangleArea(sideASizeInput,sideBSizeInput)));
    }

    public static double calculateTriangleArea(double sideASize, double sideBSize){
        double triangleArea = sideASize * sideBSize;
        return triangleArea;
    }



}
