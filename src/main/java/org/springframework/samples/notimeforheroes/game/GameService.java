package org.springframework.samples.notimeforheroes.game;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository gameRepository;


    @Autowired
    public GameService(GameRepository repository){
        this.gameRepository = repository;
    }

    public List<Game> gameList(){
        return gameRepository.findAll();
    }

    public void createGame(Game game) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) auth.getPrincipal();
        String username = currentUser.getUsername();

        
        Game newGame = new Game();
        newGame.setUsername(username);
        newGame.setHasScenes(game.isHasScenes());
        newGame.setMinPlayers(game.getMinPlayers());
        newGame.setMaxPlayers(game.getMaxPlayers());
        newGame.setState(GameState.LOBBY);
        newGame.setStartTime(new Date());
        gameRepository.save(newGame);
    }
    /*
    public void createGame(Game game){

        

    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) auth.getPrincipal();
		String username = currentUser.getUsername();
		game.setUsername(username);
    	game.setState(GameState.LOBBY);
    	gameRepository.save(game);
    	

    } */






    
}
