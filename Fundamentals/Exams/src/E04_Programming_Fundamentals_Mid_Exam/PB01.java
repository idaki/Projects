package E04_Programming_Fundamentals_Mid_Exam;

import java.util.Scanner;

public class PB01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double foodKg = Double.parseDouble(scanner.nextLine());
        double hayKg = Double.parseDouble(scanner.nextLine());
        double coverKg = Double.parseDouble(scanner.nextLine());
        double weightKG = Double.parseDouble(scanner.nextLine());
        double foodLeft = foodKg * 1000, hayLeft = hayKg * 1000
                        , coverLeft = coverKg * 1000, weightGR = weightKG * 1000;

        boolean isFoodEnough = true;
        for (int i = 1; i <= 30 ; i++) {
        foodLeft -= 300;
        if (i % 2 == 0){
            hayLeft -= 0.05 * foodLeft;
        }
        if ( i % 3 == 0){
            coverLeft -= (double) 1 / 3 * weightGR;
        }
        if (foodLeft < 0 || hayLeft <0 || coverLeft <0){
            isFoodEnough = false;
            break;
        }
        }

        if(isFoodEnough){
            System.out.printf("Everything is fine! Puppy is happy! Food: %.2f, Hay: %.2f, Cover: %.2f."
                    ,foodLeft / 1000, hayLeft / 1000 , coverLeft / 1000);

        }else {
            System.out.println("Merry must go to the pet store!");
        }

    }
}
