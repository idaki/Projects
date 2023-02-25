package Lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PB04_Songs {
    static class Song{
        String type;
        String name;
        String time;

        public Song (String type, String name, String time){
            this.type = type;
            this.name = name;
            this.time = time;
        }

        public String getName(){
            return this.name;
        }
        public  String getType(){
            return this.type;
        }


    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numSongs = Integer.parseInt(scanner.nextLine());

        List <Song> songList = new ArrayList<>();
        for (int i = 0; i < numSongs ; i++) {
            String input = scanner.nextLine();
            String[] inputSongsArr = input.split("_");
            String typeInput = inputSongsArr [0];
            String nameInput = inputSongsArr [1];
            String timeInput = inputSongsArr [2];

            Song currentSong = new Song(typeInput, nameInput, timeInput);

            songList.add(currentSong);
        }
        String command = scanner.nextLine();

        if ("all".equals(command)){
            for (Song song: songList) {
                System.out.println(song.getName());
            }
        } else {
            for (Song song: songList) {
                if (command.equals(song.getType())){
                    System.out.println(song.getName());
                }
            }
        }

    }
}
