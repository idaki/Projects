package Lab;

import java.util.Scanner;

public class P05DigitsLettersAndOther {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] charArr = scanner.nextLine().toCharArray();

        StringBuilder letters = new StringBuilder();
        StringBuilder nums = new StringBuilder();
        StringBuilder specialChar = new StringBuilder();

        for (char element: charArr) {
            if (Character.isAlphabetic(element)){
                letters.append(element);
            } else if (Character.isDigit(element)) {
                nums.append(element);
            }else{
                specialChar.append(element);
            }
        }

        System.out.println(nums);
        System.out.println(letters);
        System.out.println(specialChar);
    }

}
