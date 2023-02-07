package Exercises;

import java.util.Scanner;

public class PB02Common_Elements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] firstLine = scanner.nextLine().split(" ");
        String[] secondLine = scanner.nextLine().split(" ");

        for (int index = 0; index <= secondLine.length - 1; index++) {
            for (int i = 0; i <= firstLine.length - 1; i++) {
                if(secondLine[i].equals(firstLine[index])){
                System.out.print(secondLine[i]+" ");
                }
            }
        }
    }
}
