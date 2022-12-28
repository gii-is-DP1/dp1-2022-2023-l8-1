package org.springframework.samples.notimeforheroes.game;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.card.ability.AbilityCardInGame;
import org.springframework.samples.notimeforheroes.card.enemy.EnemyService;
import org.springframework.samples.notimeforheroes.card.market.MarketService;
import org.springframework.samples.notimeforheroes.player.Player;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

    private final GameRepository gameRepository;
    @Autowired
    private EnemyService enemyService;
    @Autowired
    private MarketService marketService;
    
    @Autowired
    public GameService(GameRepository repository){
        this.gameRepository = repository;
    }

    public List<Game> gameList(){
        return gameRepository.findAll();
    }

    public List<Player> showPlayersInGame(Integer gameId){
        return gameRepository.findPlayersInGame(gameId);
    }

    @Transactional()
    public void createGame(Game game) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User currentUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        String username = currentUser.getUsername();

        Game newGame = new Game();
        newGame.setUsername(username);
        newGame.setHasScenes(game.isHasScenes());
        newGame.setMinPlayers(game.getMinPlayers());
        newGame.setMaxPlayers(game.getMaxPlayers());
        newGame.setState(GameState.LOBBY);
        newGame.setStartTime(new Date());
        //newGame.setMarketPile(marketService.addMarket(newGame));
        newGame.setMonsterPile(enemyService.addEnemies(newGame));
        
        gameRepository.save(newGame);
        
    }

	public Optional<Game> findById(int id){
		return gameRepository.findById(id);
	}

    public void saveGame(Game game){
         gameRepository.save(game);
    }

    public Player compareBet(int gameId){
        Player bestPlayerBet = new Player();
        int bestBet = 0;
        
        List<Player> playersInGame = gameRepository.findPlayersInGame(gameId);

        for(Player player : playersInGame){
            int bet = 0;
            for(AbilityCardInGame card : player.getCartasPuja()){
                bet += card.getAbilityCard().getDamage();
            }

            if(bestBet != bet){ // Si las apuestas son iguales se mira quien tiene más edad
                if(bet>bestBet){
                    bestBet = bet;
                    bestPlayerBet = player;
                }
            }else if(player.getUser().getBirthDate().isBefore(bestPlayerBet.getUser().getBirthDate())){
                bestPlayerBet = player;
            }

        }
        

        return bestPlayerBet;
    }

    
    public Player getCurrentPlayer(User user, int gameId){
        
        Game game = gameRepository.findById(gameId).get();

	    List<Player> players = game.getPlayer();
	    Player player = players.stream().filter(x->x.getUser().equals(user)).findFirst().get();

        return player;

    }
    

    
}
