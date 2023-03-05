package Exercises05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        List<Vehicle> vehicleList = new ArrayList<>();
        boolean isCloseCommand = false;

        while (!"End".equals(input = scanner.nextLine())){
            if ("Close the Catalogue".equals(input)){
                isCloseCommand = true;
                break;
            }

            String[] vehicleInfo = input.split("\\s+");
            String type = vehicleInfo[0];
            String model = vehicleInfo[1];
            String color = vehicleInfo[2];
            int horsePower = Integer.parseInt(vehicleInfo[3]);

            Vehicle vehicle = new Vehicle(type, model,color,horsePower);
            vehicleList.add(vehicle);
        }

        while (!"Close the Catalogue".equals(input = scanner.nextLine())){
            for (Vehicle element: vehicleList) {
                if (element.getModel().equals(input)){
                    element.setType();
                    System.out.println("Type: " + element.getType());
                    System.out.println("Model: " + element.getModel());
                    System.out.println("Color: " + element.getColor());
                    System.out.println("Horsepower: " + element.getHorsePower());
                }
            }
        }
        int horsePowerCar = 0, horsePowerTruck = 0, countCar =0, countTruck = 0;
        for (Vehicle element: vehicleList) {
            if (element.getType().equals("Car") || element.getType().equals("car")){
                horsePowerCar += element.getHorsePower();
                countCar++;
            }else{
               horsePowerTruck += element.getHorsePower();
               countTruck++;
            }
        }

        if (horsePowerCar > 0){
            System.out.printf("Cars have average horsepower of: %.2f.%n", (double)horsePowerCar / countCar );
        } else {
            System.out.printf("Cars have average horsepower of: %.2f.%n", 0.0);
        }
        if (horsePowerTruck > 0){
            System.out.printf("Trucks have average horsepower of: %.2f.", (double)horsePowerTruck / countTruck );
        } else{
            System.out.printf("Trucks have average horsepower of: %.2f.", 0.0 );
        }
    }
}
