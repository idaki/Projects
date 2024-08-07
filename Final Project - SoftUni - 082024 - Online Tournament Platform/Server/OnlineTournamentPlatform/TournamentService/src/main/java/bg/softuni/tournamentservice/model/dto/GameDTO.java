package bg.softuni.tournamentservice.model.dto;

import java.util.List;

//Comparable for ordering the set
public class GameDTO implements Comparable<GameDTO> {
    private Long id;
    private String title;
    private List<AssetDTO> assets;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<AssetDTO> getAssets() { return assets; }
    public void setAssets(List<AssetDTO> assets) { this.assets = assets; }

    @Override
    public int compareTo(GameDTO other) {
        return this.id.compareTo(other.id); // or compare by title if preferred
    }
}
