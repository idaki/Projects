package Lab;

import java.util.Scanner;

public class P03_Substring {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String token = scanner.nextLine();
        String text = scanner.nextLine();
        int index = text.indexOf(token);
        while (index!=-1){
           text = text.replace(token,"");
           index = text.indexOf(token);
        }
        System.out.println(text);

    }
}
