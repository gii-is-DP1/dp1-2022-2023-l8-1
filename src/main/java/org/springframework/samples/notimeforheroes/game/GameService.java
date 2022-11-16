package org.springframework.samples.notimeforheroes.game;

import java.util.List;
import java.util.Optional;

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

    public void createGame(Game game){

        

    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) auth.getPrincipal();
		String username = currentUser.getUsername();
		game.setUsername(username);
    	game.setState(GameState.LOBBY);
    	gameRepository.save(game);
    	

    }
    public Optional<Game> findById(int id){
    	return gameRepository.findById(id);
    }
    
}
