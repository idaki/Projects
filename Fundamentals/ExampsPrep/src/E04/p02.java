package E04;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String regex = "@#+(?<barcode>[A-Z][A-Za-z0-9]{4,}[A-Z])@#+";
        Pattern pattern = Pattern.compile(regex);
        String regexNum = "\\d";
        Pattern patternNum = Pattern.compile(regexNum);
        for (int i = 0; i < n; i++) {
            boolean isValid = false;
            String text = scanner.nextLine();
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                 isValid = true;
                String productName = matcher.group("barcode");
                String productGroup="";
                for (char element: productName.toCharArray()) {
                    if (Character.isDigit(element)){
                        productGroup+=element;
                    }


               }  if (!productGroup.isEmpty()) {
                    System.out.println("Product group: " + productGroup);
                } else {
                    System.out.println("Product group: 00");
                }
            }
            if (!isValid){
                System.out.println("Invalid barcode");
            }
        }
    }
}
