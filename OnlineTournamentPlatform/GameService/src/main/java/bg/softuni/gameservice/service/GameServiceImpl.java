package bg.softuni.gameservice.service;

import bg.softuni.crudservice.crud.CrudServiceImpl;
import bg.softuni.gameservice.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl extends CrudServiceImpl<Game, Long> implements GameService {


    public GameServiceImpl(JpaRepository<Game, Long> repository) {
        super(repository);
    }
}
