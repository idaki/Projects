package bg.softuni.gameservice.service.impl;

import bg.softuni.crudservice.crud.CrudServiceImpl;
import bg.softuni.gameservice.model.Asset;
import bg.softuni.gameservice.repository.AssetRepository;
import bg.softuni.gameservice.service.AssetService;
import org.springframework.data.jpa.repository.JpaRepository;

public class AssetServiceImpl extends CrudServiceImpl<Asset, Long> implements AssetService {

private final AssetRepository assetRepository;

    public AssetServiceImpl(JpaRepository<Asset, Long> repository, AssetRepository assetRepository) {
        super(repository);
        this.assetRepository = assetRepository;
    }
}
