package ExercisePB02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputLine = scanner.nextLine().split(",");
        String title = inputLine[0];
        String author = inputLine[1];
        String content = inputLine[2];
        int n = Integer.parseInt(scanner.nextLine());
        Article article = new Article(title,author,content);

        for (int i = 0; i < n ; i++) {
            String[] command = scanner.nextLine().split(":");
            String operation = command[0];
            String token = command [1];

            if ("Edit".equals(operation)){
               article.edit(token);
            } else if ("ChangeAuthor".equals(operation)) {
                article.changeAuthor(token);
            } else if ("Rename".equals(operation)) {
                article.rename(token);
            }
        }
        System.out.println(article.toString());
    }
}
