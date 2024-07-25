package bg.softuni.tournamentservice.model.ExportDto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameTitleDTO implements Comparable<GameTitleDTO> {
    @JsonProperty("title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int compareTo(GameTitleDTO other) {
        return this.title.compareTo(other.title);
    }
}
