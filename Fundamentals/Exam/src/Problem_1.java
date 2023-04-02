import java.util.Scanner;

public class Problem_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //StringBuilder text = new StringBuilder(scanner.nextLine());

        String text = scanner.nextLine();
        String input = "";

        while (!"Done".equals(input = scanner.nextLine())) {
            String[] commandArr = input.split(" ");
            if (input.contains("Change")) {
                char oldChar = commandArr[1].charAt(0);
                char newChar = commandArr[2].charAt(0);
                text = text.replace(oldChar, newChar);
                System.out.println(text);

            } else if (input.contains("Includes")) {
                String substringTest = commandArr[1];
                if (text.contains(substringTest)){
                    System.out.println("True");
                } else{
                    System.out.println("False");
                }

            } else if (input.contains("End")) {
                String substringTest = commandArr[1];
                if (text.endsWith(substringTest)){
                    System.out.println("True");
                } else{
                    System.out.println("False");
                }

            } else if (input.contains("Uppercase")) {
                text = text.toUpperCase();
                System.out.println(text);


            } else if (input.contains("FindIndex")) {
                char testChar = commandArr[1].charAt(0);
                int index = text.indexOf(testChar);
                System.out.println(index);

            } else if (input.contains("Cut")) {
                int indexStart = Integer.parseInt(commandArr[1]);
                int indexCount = Integer.parseInt(commandArr[2]);
                int endIndex =indexStart+indexCount;
                text = text.substring(indexStart,endIndex);
                System.out.println(text);

            }

        }

    }
}
