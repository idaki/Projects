package bg.softuni.tournamentservice.model.viewDto;


import bg.softuni.gameservice.model.Asset;
import bg.softuni.gameservice.model.ExportDto.GameDTO;

import java.util.Set;

public class TournamentDTO {
    private Long id;
    private String name;
    private String description;
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
