package Exercises;

import java.util.Scanner;

public class p03_ExtractFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
       // String[] folders = path.split("\\\\");
      //  String file = folders[folders.length-1];
        int lastIndex = path.lastIndexOf("\\");
        String file = path.substring(lastIndex+1);

        int index = file.indexOf('.');
        String fileName = file.substring(0,index);
        String extension = file.substring(index+1);
        System.out.println("File name: " + fileName);
        System.out.println("File extension: " + extension);

    }
}
