package org.springframework.samples.notimeforheroes.game;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.card.ability.AbilityCardInGame;
import org.springframework.samples.notimeforheroes.card.ability.AbilityService;
import org.springframework.samples.notimeforheroes.card.enemy.EnemyService;
import org.springframework.samples.notimeforheroes.card.market.MarketCard;
import org.springframework.samples.notimeforheroes.card.market.MarketCardInGame;
import org.springframework.samples.notimeforheroes.card.market.MarketService;
import org.springframework.samples.notimeforheroes.player.Player;
import org.springframework.samples.notimeforheroes.player.PlayerService;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

    //Repositorios y servicios como variables y posterior asociación a este servicio
    private final GameRepository gameRepository;
    @Autowired
    private EnemyService enemyService;
    @Autowired
    private MarketService marketService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private AbilityService abilityService;
    
    @Autowired
    public GameService(GameRepository repository){
        this.gameRepository = repository;
    }

    //Encuentra todos los juegos y los asocia a una lista
    public List<Game> gameList(){
        return gameRepository.findAll();
    }

    //Encuentra la lista de Player asociada a un Game por su id
    public List<Player> showPlayersInGame(Integer gameId){
        return gameRepository.findPlayersInGame(gameId);
    }

    //Patrón builder para la creación de un juego
    @Transactional()
    public void createGame(Game game) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User currentUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        String username = currentUser.getUsername();

        Game newGame = Game.builder().username(username).hasScenes(game.isHasScenes()).minPlayers(game.getMinPlayers()).maxPlayers(game.getMaxPlayers())
        		.state(GameState.LOBBY).startTime(new Date()).build();
        newGame.setMarketPile(marketService.addMarket(newGame));
        gameRepository.save(newGame);
    }
    //poblar los enemigos de la partida según el número de jugadores
    //2players:19enemies, 3players:23enemies, 4players:27enemies
    public void insertMonsterPile(int numPlayers) {
    	int lastId = gameList().size();//TODO METER EL ID DEL JUEGO COMO VARIABLE A LA FUNCION
    	int numCards=0;
    	switch(numPlayers) {
	    	case 2:{
	    		numCards=19;
	    		break;
	    	}
	    	case 3:{
	    		numCards=23;
	    		break;
	    	}
	    	case 4: {
	    		numCards=27;
	    		break;
	    	}
    	}
    	Game last = gameRepository.findById(lastId).get();
    	last.setMonsterPile(enemyService.addEnemies(last,numCards));
    }

    //Encontrar Game por id
	public Optional<Game> findById(int id){
		return gameRepository.findById(id);
	}

    //funcion save simple
    public void saveGame(Game game){
         gameRepository.save(game);
    }

    //Funcion para la comparación de puntuación para la elección de lider
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

    //Funcion para encontrar el jugador actual
    public Player getCurrentPlayer(User user, int gameId){
        
        Game game = gameRepository.findById(gameId).get();

	    List<Player> players = game.getPlayer();
	    Player player = players.stream().filter(x->x.getUser().equals(user)).findFirst().get();

        return player;

    }

    //funcion para la compra de cartas del mercado por un jugador
    public void buyCard(User user, int gameId, int marketCardId){

        Player currentPlayer = getCurrentPlayer(user, gameId);
        

        List<MarketCardInGame> marketHand = currentPlayer.getMarketHand(); // Obtengo la lista de cartas de mercado que tiene el jugador
        
        MarketCardInGame currentMarketCard = marketService.findById(marketCardId); // Obtengo la carta seleccionada para comprar dado su id
        MarketCard marketCard = currentMarketCard.getMarketCard();


        // Comprobamos si el jugador tiene suficiente oro para comprar la carta y si su héroe es compatible con la carta
        if((currentPlayer.getGold() >= marketCard.getPrice()) && 
        ((marketCard.getProfiency1().equals(currentPlayer.getProfiency())) ||
        (marketCard.getProfiency2().equals(currentPlayer.getProfiency())) ||
        (marketCard.getProfiency3().equals(currentPlayer.getProfiency())) ||
        (marketCard.getProfiency4().equals(currentPlayer.getProfiency())))){
            currentMarketCard.setPlayer(currentPlayer);

            marketHand.add(currentMarketCard); // Compramos la carta añadiendola a la lista de cartas de mercado del jugador
            marketService.addCardToMarket(currentMarketCard, gameId);


            currentPlayer.setMarketHand(marketHand); // Seteo la lista de cartas de mercado del jugador con la lista que tenemos en la que hemos añadido la carta
            int totalGold = currentPlayer.getGold();
            totalGold = totalGold - currentMarketCard.getMarketCard().getPrice();
            currentPlayer.setGold(totalGold); // Restamos el oro que cuesta la carta de mercado
            playerService.savePlayer(currentPlayer); // Guardamos el jugador en la bd
        }
        // System.out.println("Mano de mercado del jugador: "+currentPlayer.getMarketHand());
    }
    
    public void discardAbilityCard(User user, int gameId, int abilityCardId){

        Player currentPlayer = getCurrentPlayer(user, gameId);

        List<AbilityCardInGame> currentAbilityHand = currentPlayer.getAbilityHand();

        AbilityCardInGame currentCard = abilityService.findById(abilityCardId);
        currentAbilityHand.remove(currentCard);
        
        currentCard.setPlayerDiscard(currentPlayer);
        currentCard.setPlayer(null);
        abilityService.saveAbilityCardInGame(currentCard);

        currentPlayer.setAbilityHand(currentAbilityHand);
        playerService.savePlayer(currentPlayer);


    }

    public void discardMarketCard(User user, int gameId, int marketCardId) {

        Player currentPlayer = getCurrentPlayer(user, gameId);
        MarketCardInGame marketCard = marketService.findById(marketCardId);

        List<MarketCardInGame> currentMarketHand = currentPlayer.getMarketHand();
        currentMarketHand.remove(marketCard);

        marketCard.setPlayerMarketDiscard(currentPlayer);
        // marketCard.setPlayer(null);
        marketService.saveMarketCardInGame(marketCard);
        
        currentPlayer.setMarketHand(currentMarketHand);
        playerService.savePlayer(currentPlayer);

        int i = 0;
        Math.abs(i);


    }

    public void stealCard(Player player){
        List<AbilityCardInGame> pile = player.getAbilityPile();
        List<AbilityCardInGame> hand = player.getAbilityHand();
        List<AbilityCardInGame> discards= player.getDiscardPile();

        if(pile.size()==0){
            pile = discards;
            Collections.shuffle(pile);
            for(int i = 0; i<pile.size(); i++){
                pile.get(i).setPlayerDiscard(null);
                // pile.get(i).setPlayer(null);
                pile.get(i).setPlayerPile(player);
                System.out.println("Cartas en descarte: "+pile.get(i).getPlayerPile());
                // discards.remove(c);
                abilityService.saveAbilityCardInGame(pile.get(i));
                
            }
            // player.setAbilityPile(pile);
            // player.setDiscardPile(discards);



            playerService.savePlayer(player);
        }

        AbilityCardInGame card = pile.get(0);
        card.setPlayer(player);
        card.setPlayerPile(null);

        pile.remove(card);
        hand.add(card);
        
        abilityService.saveAbilityCardInGame(card);
        playerService.savePlayer(player);



    }

}
