package bg.softuni.tournamentservice.service.impl;


import bg.softuni.tournamentservice.model.Asset;
import bg.softuni.tournamentservice.repository.AssetRepository;
import bg.softuni.tournamentservice.service.AssetService;
import org.springframework.data.jpa.repository.JpaRepository;

public class AssetServiceImpl  implements AssetService {

private final AssetRepository assetRepository;

    public AssetServiceImpl(JpaRepository<Asset, Long> repository, AssetRepository assetRepository) {

        this.assetRepository = assetRepository;
    }

}
