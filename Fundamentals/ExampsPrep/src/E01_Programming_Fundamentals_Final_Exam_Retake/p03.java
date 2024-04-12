package E01_Programming_Fundamentals_Final_Exam_Retake;

import java.util.*;

public class p03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, List> pieceMap = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] inputSongData = scanner.nextLine().split("\\|");
            String piece = inputSongData[0];
            String composer = inputSongData[1];
            String key = inputSongData[2];

            if (!pieceMap.containsKey(piece)) {
                pieceMap.put(piece, new ArrayList<>());
            }
            pieceMap.get(piece).add(composer);
            pieceMap.get(piece).add(key);
        }

        String input = "";
        while (!"Stop".equals(input = scanner.nextLine())) {
            String[] inputStr = input.split("\\|");

            if ( input.contains("Add")){
                String piece = inputStr[1];
                String composer = inputStr[2];
                String key = inputStr[3];
                if (!pieceMap.containsKey(piece)) {
                    pieceMap.put(piece, new ArrayList<>());
                    pieceMap.get(piece).add(composer);
                    pieceMap.get(piece).add(key);
                    System.out.printf("%s by %s in %s added to the collection!%n",piece,composer,key);
                } else {
                    System.out.printf("%s is already in the collection!%n",piece);
                }
            }else if(input.contains("Remove")){
                String piece = inputStr[1];
                if(pieceMap.containsKey(piece)){
                    pieceMap.remove(piece);
                    System.out.printf("Successfully removed %s!%n",piece);
                } else{
                    System.out.printf("Invalid operation! %s does not exist in the collection.%n",piece);
                }

            } else if(input.contains("ChangeKey")){
                String piece = inputStr[1];
                String key = inputStr[2];
                if (pieceMap.containsKey(piece)){
                    pieceMap.get(piece).remove(1);
                    pieceMap.get(piece).add(key);
                    System.out.printf("Changed the key of %s to %s!%n",piece,key);
                }else{
                    System.out.printf("Invalid operation! %s does not exist in the collection.%n",piece);
                }
            }
        }

        for (Map.Entry<String, List> entry: pieceMap.entrySet()) {
            String piece = entry.getKey();
            String composer = entry.getValue().get(0).toString();
            String key = entry.getValue().get(1).toString();
            System.out.printf("%s -> Composer: %s, Key: %s%n",piece, composer,key);


        }


    }
}
