package org.springframework.samples.notimeforheroes.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository repo;


    @Autowired
    public GameService(GameRepository repository){
        this.repo = repository;
    }

    public List<Game> gameList(){
        return repo.findAll();
    }

    public void createGame(Game game){
        repo.save(game);
    }
    
}
