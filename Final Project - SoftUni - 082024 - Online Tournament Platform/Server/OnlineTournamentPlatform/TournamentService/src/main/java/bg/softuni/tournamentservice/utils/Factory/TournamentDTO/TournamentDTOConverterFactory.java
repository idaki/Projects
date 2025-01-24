package bg.softuni.tournamentservice.utils.Factory.TournamentDTO;

import bg.softuni.tournamentservice.model.Asset;
import bg.softuni.tournamentservice.model.Game;
import bg.softuni.tournamentservice.model.Tournament;
import bg.softuni.tournamentservice.model.dto.TournamentDTO;

import org.modelmapper.ModelMapper;

import java.util.Set;

public class TournamentDTOConverterFactory{

    private  final ModelMapper modelMapper;

    public TournamentDTOConverterFactory(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public  TournamentDTO convert(Tournament tournament) {
        TournamentDTO dto = modelMapper.map(tournament, TournamentDTO.class);

        // Handle Game and Asset-specific logic
        Game game = tournament.getGame();
        if (game != null) {
            String description = "PLACEHOLDER TEXT"; // Replace with actual logic
            Set<Asset> assets = game.getAssets();
            String img = "";
            if (assets != null && !assets.isEmpty()) {
                img = assets.iterator().next().getUrl();
            }
            dto.setDescription(description);
            dto.setUrl(img);
        } else {
            dto.setDescription("No game associated");
            dto.setUrl(""); // Default or placeholder URL
        }

        return dto;
    }
}
