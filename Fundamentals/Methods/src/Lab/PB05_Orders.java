package Lab;

import java.util.Scanner;

public class PB05_Orders {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String productNameInput = scanner.nextLine();
        int quantityInput = Integer.parseInt(scanner.nextLine());
        getOrderPrice(productNameInput, quantityInput);
    }

    public static void getOrderPrice(String productName, int quantity) {
        double orderPrice = 0;
        if ("coffee".equals(productName)) {
            orderPrice = quantity * 1.5;
        } else if ("water".equals(productName)) {
            orderPrice = quantity;
        } else if ("coke".equals(productName)) {
            orderPrice = quantity * 1.4;
        } else if ("snacks".equals(productName)) {
            orderPrice = quantity * 2;
        }
        System.out.printf("%.2f", orderPrice);
    }
}
