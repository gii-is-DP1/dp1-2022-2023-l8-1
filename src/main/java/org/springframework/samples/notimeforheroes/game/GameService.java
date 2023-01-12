package org.springframework.samples.notimeforheroes.game;


import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.ArrayList;

import javax.persistence.EnumType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.card.ConditionType;
import org.springframework.samples.notimeforheroes.card.ability.AbilityCardInGame;
import org.springframework.samples.notimeforheroes.card.ability.AbilityCardInGameRepository;
import org.springframework.samples.notimeforheroes.card.ability.AbilityService;
import org.springframework.samples.notimeforheroes.card.enemy.Enemy;
import org.springframework.samples.notimeforheroes.card.ability.AbilityType;
import org.springframework.samples.notimeforheroes.card.enemy.EnemyInGame;
import org.springframework.samples.notimeforheroes.card.enemy.EnemyService;
import org.springframework.samples.notimeforheroes.card.enemy.EnemyType;
import org.springframework.samples.notimeforheroes.card.market.MarketCard;
import org.springframework.samples.notimeforheroes.card.market.MarketCardInGame;
import org.springframework.samples.notimeforheroes.card.market.MarketCardType;
import org.springframework.samples.notimeforheroes.card.market.MarketService;
import org.springframework.samples.notimeforheroes.player.HeroType;
import org.springframework.samples.notimeforheroes.player.Player;
import org.springframework.samples.notimeforheroes.player.PlayerService;
import org.springframework.samples.notimeforheroes.player.Profiency;
import org.springframework.samples.notimeforheroes.turn.PhaseType;
import org.springframework.samples.notimeforheroes.turn.Turn;
import org.springframework.samples.notimeforheroes.turn.TurnRepository;
import org.springframework.samples.notimeforheroes.turn.TurnService;
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
    private TurnService turnService;
    
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
    	int lastId = gameList().size(); //TODO METER EL ID DEL JUEGO COMO VARIABLE A LA FUNCION
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
    
    //comprueba cuantos enemigos hay y rellena los enemigos según las normas
    //si quedan 0 se añaden 3 si quedan 1 o 2 se añade 1, si quedan 3 no se hace nada
  	public void resupplyEnemies(int gameId) {
  		Game currentGame = findById(gameId).get();
  		int currentNumEnemies = currentGame.getMonsterField().size();//los que quedan
  		int enemiesToAdd=0;//los que habría que añadir
  		if (currentNumEnemies==0) {
  			enemiesToAdd=3;
  		}else if(currentNumEnemies==1 || currentNumEnemies==2) {
  			enemiesToAdd=1;
  		}
  		enemyService.enemyToField(currentGame, enemiesToAdd);
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
	    Player player = players.stream().filter(x -> x.getUser().equals(user)).findFirst().get();

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
            
            AbilityType ability = (AbilityType) AbilityType.valueOf(currentMarketCard.getMarketCard().getType().toString());
            List<AbilityCardInGame> ability_cards = abilityService.findAll().stream().filter(x -> x.getAbilityType().equals(ability)).map(card -> abilityService.createHandInPlayer(currentPlayer, card)).collect(Collectors.toList());
            abilityService.saveAbilityCardInGame(ability_cards.get(0));

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

        Player currentPlayer = getCurrentPlayer(user, gameId); // Jugador actual

        List<AbilityCardInGame> currentAbilityHand = currentPlayer.getAbilityHand(); //Cartas en mano

        AbilityCardInGame currentCard = abilityService.findById(abilityCardId); // Carta a mandar al descarte
        currentAbilityHand.remove(currentCard); // La quitamos de la lista
        
        currentCard.setPlayerDiscard(currentPlayer); // La relacionamos con el descarte
        currentCard.setPlayer(null); // La desrelacionamos con la mano
        abilityService.saveAbilityCardInGame(currentCard); // Guardamos los cambios en la carta

        currentPlayer.setAbilityHand(currentAbilityHand); // Reasignamos la mano al jugador
        playerService.savePlayer(currentPlayer); // Guardamos los cambios
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

    // Mueve una carta específica del mazo a la mano
    //
    public void recoverCard(int cardId, Player player){
        AbilityCardInGame card = abilityService.findById(cardId);

        card.setPlayerDiscard(null);
        card.setPlayer(player);

        List<AbilityCardInGame> hand = player.getAbilityHand();
        List<AbilityCardInGame> discard = player.getDiscardPile();
        hand.add(card);
        discard.remove(card);

        player.setAbilityHand(hand);
        player.setDiscardPile(discard);
        
        playerService.savePlayer(player);
        abilityService.saveAbilityCardInGame(card);
        
    }

    // Mover carta del fondo del mazo de descartes al del de robo
    // RECUPERAR "N" CARTAS
    public void regainCards(Player player, int number_of_cards){

        List<AbilityCardInGame> pile = player.getAbilityPile(); //La pila de robo
        List<AbilityCardInGame> discard = player.getDiscardPile(); //La pila de descartes

        if(discard.size() < number_of_cards){
            number_of_cards= discard.size();
        }

        for(int i = 0; i < number_of_cards; i++){
            AbilityCardInGame card = discard.get(0); // La carta que está en el fondo de la pila de descartes

            pile.add(card); // La pongo en el fondo del mazo de robo
            discard.remove(card); // La quito de la lista de los descartes

            card.setPlayerDiscard(null); // La quito del descarte
            card.setPlayerPile(player); // Le asigno la pila de robo
            abilityService.saveAbilityCardInGame(card); // Guardo los datos
        }

        player.setAbilityPile(pile); 
        player.setDiscardPile(discard); //Actualizo las listas de robo y descarte

        playerService.savePlayer(player); // Guardo al jugador con las nuevas listas
        
    }

    // Robar cartas del mazo, contando conque si voy a robar más de lo que hay en el mazo tengo que barajar primero para seguir robando
    // ROBA N CARTAS
    public void drawCards(Player player, int number_of_cards){

        List<AbilityCardInGame> pile = player.getAbilityPile();
        List<AbilityCardInGame> hand = player.getAbilityHand();
        List<AbilityCardInGame> discards= player.getDiscardPile(); // Saco las listas de la pila de robo, la mano y el mazo de descartes
        int cards_drawn = 0; // Esto servirá para en caso de que el mazo de robo sea más pequeño que el número de cartas a robar pueda limitar el robo al rebarajarlo

        if(pile.size() < number_of_cards){ // Si voy a robar más cartas de las que tengo en el mazo
            for(int i = 0; i < pile.size(); i++){ // Aquí robo las cartas que quedan en la pila de robo
                pile.get(0).setPlayer(player);
                hand.add(pile.get(0)); //Me meto la carta en la lista de la mano

                pile.get(0).setPlayerPile(null);
                pile.remove(pile.get(i)); // Elimino la cartas que he robado de la pila

                abilityService.saveAbilityCardInGame(pile.get(0)); //Guardo los cambios de la carta
                cards_drawn++; //Contador de cartas robadas

            } // Aquí la pile de robo debería estar vacía

            for(int i = 0; i < discards.size(); i++){ //Recorro las cartas del desgaste
                discards.get(0).setPlayerPile(player);
                pile.add(discards.get(0)); //Meto la carta en el mazo de robo

                discards.get(0).setPlayerDiscard(null);
                discards.remove(discards.get(0)); // Las elimino de la pila de descartes

                abilityService.saveAbilityCardInGame(discards.get(0)); //Guardo los cambios en la carta

            }

            Collections.shuffle(pile); //Barajo el mazo de robo
            player.setWounds(player.getWounds()-1); //Añado una herida a mi héroe por haber barajado
            player.setDiscardPile(discards); //Asigno la pila de descarte recompuesta al player
        }

        for(int i = 0; i < number_of_cards-cards_drawn; i++){ // Aqui robo de la pila de robo barajada las cartas restantes a la mano
            AbilityCardInGame card = pile.get(0);

            card.setPlayer(player);
            hand.add(card); // Me la llevo a la mano

            card.setPlayerPile(null);
            pile.remove(card); // La quito de la pila de robo
            
            abilityService.saveAbilityCardInGame(card);
        }
        player.setAbilityPile(pile); //Asigno la pila de robo recompuesta al player
        player.setAbilityHand(hand); //Asigno la mano recompuesta al player
        playerService.savePlayer(player); //Guardo aqui los cambios a las 3 relaciones de player

    }

    // Mover carta del fondo del mazo de descartes al fondo del robo
    // RECUPERAR "N" CARTAS
    public void loseCards(Player player, int number_of_cards){

        List<AbilityCardInGame> pile = player.getAbilityPile();
        List<AbilityCardInGame> discards= player.getDiscardPile(); // Saco las listas de la pila de robo, la mano y el mazo de descartes
        int cards_lost = 0; // Esto servirá para en caso de que el mazo de robo sea más pequeño que el número de cartas a robar pueda limitar el robo al rebarajarlo

        if(pile.size() < number_of_cards){ // Si voy a perder más cartas de las que tengo en el mazo
            for(int i = 0; i < pile.size(); i++){ // Aquí robo las cartas que quedan en la pila de robo
                pile.get(0).setPlayerDiscard(player);
                discards.add(pile.get(0)); //Me meto la carta en la lista de la pila de desgaste

                pile.get(0).setPlayerPile(null);
                pile.remove(pile.get(i)); // Elimino la cartas que he perdido de la pila

                abilityService.saveAbilityCardInGame(pile.get(0)); //Guardo los cambios de la carta
                cards_lost++; //Contador de cartas robadas

            } // Aquí la pile de robo debería estar vacía

            for(int i = 0; i < discards.size(); i++){ //Recorro las cartas del desgaste
                discards.get(0).setPlayerPile(player);
                pile.add(discards.get(0)); //Meto la carta en el mazo de robo

                discards.get(0).setPlayerDiscard(null);
                discards.remove(discards.get(0)); // Las elimino de la pila de descartes

                abilityService.saveAbilityCardInGame(discards.get(0)); //Guardo los cambios en la carta

            } // Aquí debería de estar lleno el robo y vacío los descartes

            Collections.shuffle(pile); //Barajo el mazo de robo
            player.setWounds(player.getWounds()-1); //Añado una herida a mi héroe por haber barajado
            player.setDiscardPile(discards); //Asigno la pila de descarte recompuesta al player
        }

        for(int i = 0; i < number_of_cards-cards_lost; i++){ // Aqui robo de la pila de robo barajada las cartas restantes a la mano
            AbilityCardInGame card = pile.get(0);

            card.setPlayerDiscard(player);
            discards.add(card); // Me la llevo al descarte

            card.setPlayerPile(null);
            pile.remove(card); // La quito de la pila de robo
            
            abilityService.saveAbilityCardInGame(card);
        }
        player.setAbilityPile(pile); //Asigno la pila de robo recompuesta al player
        player.setDiscardPile(discards); //Asigno la pila de descarte recompuesta al player
        playerService.savePlayer(player); //Guardo aqui los cambios a las 3 relaciones de player
        
    }

    @Transactional
    //Dañar a un enemigo y si fuera a matarlo me otorga su gloria y oro, sumandome su baja, considerando los efectos de los bosses
	public void damageEnemy (Player player, EnemyInGame enemy, AbilityCardInGame card, int damage, int addGold){ 
        //Jugador que ataca, enemigo al que ataca, Carta que usa, Daño de la carta bajo las condiciones pertienentes, Oro adicional que 
        //pueda ser necesario si el enemigo fue asesinado por una carta en específico como ATAQUE_FURTIVO

		int current_enemy_wounds = enemy.getWounds(); // Las heridas que tiene mi enemigo
        int endurance = enemy.getEnemy().getEndurance(); // La fortitud del mismo
        int life_total = endurance-current_enemy_wounds; // El daño necesario para matarlo
        int glory = player.getGlory(); // Gloria actual del jugador

        if (life_total > damage){ // Caso donde el enemigo sobrevive
                enemy.setWounds(current_enemy_wounds + damage); // Recalculo las heridas del enemigo
                enemyService.saveEnemyInGame(enemy); // Guardo la actualización del enemy
            if(enemy.getEnemy().getIsBoss()){ // Es un boss){

                player.setGlory(glory + 1); // Como he dañado a un jefe gano 1 de Gloria
                playerService.savePlayer(player); // Guardo los cambios a player

                if(enemy.getEnemy().getType().equals(EnemyType.GURDRUG)){ // Si atacas a GUDRUG pierdes 1 carta
                    loseCards(player, 1);
                }else if(enemy.getEnemy().getType().equals(EnemyType.SHRIEKKNIFER) && damage == 1){ // Si atacas a SHRIEKKNIFER con cartas de 1 de daño recuperas 1 carta
                    regainCards(player, 1);
                }
            }
        }else{  
            if(enemy.getEnemy().getIsBoss()){ // Es un boss){
                glory++; //Incremento la gloria que le voy a asignar luego
                if(enemy.getEnemy().getType().equals(EnemyType.GURDRUG)){ // Si atacas a GUDRUG pierdes 1 carta
                    loseCards(player, 1);
                }else if(enemy.getEnemy().getType().equals(EnemyType.SHRIEKKNIFER) && damage == 1){ // Si atacas a SHRIEKKNIFER con cartas de que le hagan 1 punto de daño recuperas 1 carta
                    regainCards(player, 1);
                }
            }
                int kills = player.getEnemy_kills(); 
                int gold = player.getGold(); // Recojo las kills y el oro

                player.setEnemy_kills(kills + 1);
                player.setGlory( glory + enemy.getEnemy().getGlory());
                player.setGold( gold + enemy.getEnemy().getGold() + addGold); // Recalculo los campos del jugador

                playerService.savePlayer(player); // Actualizo el jugador en la DB

                List<EnemyInGame> field = enemy.getGameField().getMonsterField(); // Me traigo el campo de Batalla
                field.remove(enemy);
                player.getGame().setMonsterField(field); // Actualizo el monsterfield donde se encontraba el enemigo en el Game
                enemy.setGameField(null); // Elimino la relación del enemigo con el campo

                enemyService.saveEnemyInGame(enemy);
                gameRepository.save(player.getGame()); //Actualizo el enemigo y el game

                List<AbilityCardInGame> cards_used_on = enemy.getCardsPlayed();
                for (AbilityCardInGame c:cards_used_on){
                    c.setEnemyInGame(null);
                    abilityService.saveAbilityCardInGame(c); //Elimino la relación de cada carta con el Enemy que fue derrotado

                }
                List<AbilityCardInGame> void_list = enemy.getCardsPlayed();
                void_list.clear();
                enemy.setCardsPlayed(void_list);
                enemyService.saveEnemyInGame(enemy);
        }
	}
    
    @Transactional
	public void reduceDamage (Turn turn, int reduction){ //Asigno el número de daño a reducir este turno, entra el turno y número fijo a sumar
		turn.setDamageReduction(turn.getDamageReduction() + reduction);
		turnService.save(turn);
		}

    public void recoverMarketCard(int marketCardId, Player player){
        MarketCardInGame card = marketService.findById(marketCardId);

        card.setPlayerMarketDiscard(null);
        card.setPlayer(player);

        List<MarketCardInGame> hand = player.getMarketHand();
        List<MarketCardInGame> discard = player.getMarketDiscardPile();
        hand.add(card);
        discard.remove(card);

        player.setMarketHand(hand);
        player.setMarketDiscardPile(discard);
        
        playerService.savePlayer(player);
        marketService.saveMarketCardInGame(card);
        
    }

    public void roghkiller(Game game){

        List<EnemyInGame> enemies = game.getMonsterField();

        for(EnemyInGame e: enemies){
            Enemy enemy = e.getEnemy();
            enemy.setEndurance(enemy.getEndurance()+1);
            e.setEnemy(enemy);
            enemyService.saveEnemyInGame(e);
        }
        }
    public void changeEnemy(int enemyId, int gameId){

        Game game = findById(gameId).get();

        EnemyInGame selectedEnemy = enemyService.findById(enemyId).get();
        selectedEnemy.setGameField(null);
        selectedEnemy.setGame(game);

        List<EnemyInGame> enemies = game.getMonsterPile();
        EnemyInGame nextEnemy = enemies.get(0); // Obtengo el enemigo al final del mazo
        nextEnemy.setGameField(game);
        nextEnemy.setGame(null);

        enemyService.saveEnemyInGame(nextEnemy);
        enemyService.saveEnemyInGame(selectedEnemy);
        


    }
    
    @Transactional
    public void registerCardUsage(Turn turn, EnemyInGame enemy, AbilityCardInGame card){

        List<AbilityCardInGame> cards_used = turn.getCardsPlayed(); // Cartas jugadas este turno

        if(card.getAbilityCard().getTarget()){
            List<AbilityCardInGame> cards_played_on = enemy.getCardsPlayed(); // Cartas jugadas sobre el enemigo actualmente

            card.setEnemyInGame(enemy); // Relacionar carta con enemigo
            card.setTurn(turn); // Relaciona con el turno
            card.setPlayerDiscard(turn.getPlayer()); // Al descarte
            card.setPlayer(null); // Fuera de la mano
            cards_used.add(card); // Añadir a la lista de cartas usadas usadas este truno
            cards_played_on.add(card); // Añadir a la lista de cartas sobre el enemigo

            turn.setCardsPlayed(cards_used); // Setear la lista de cartas usadas este turno
            enemy.setCardsPlayed(cards_played_on); // Setear la lista de cartas usadas sobre el enemigo

            turnService.save(turn); // Guardo los cambios en el turno
            enemyService.saveEnemyInGame(enemy); // Guardo los cambios del enemigo
            abilityService.saveAbilityCardInGame(card); // // Guardo los cambios de LA CARTA

            if(card.getAbilityCard().getAbilityType().equals(AbilityType.DAGA_ELFICA) && 
            (turn.getPlayer().getProfiency()==Profiency.PERICIA || turn.getPlayer().getSecondProfiency()==Profiency.PERICIA)){
                // Caso especial de uso de carta, la Daga Élfica se recupera directamente si el Héroe que la usa tiene Pericia como Profiency
                recoverCard(card.getId(), turn.getPlayer());
            }

        }else{

            if(card.getAbilityCard().getCondition()!=null && card.getAbilityCard().getCondition().equals(ConditionType.USO_UNICO)){

                Player currentPlayer = turn.getPlayer(); // Jugador actual
                List<AbilityCardInGame> currentAbilityHand = currentPlayer.getAbilityHand();

                currentAbilityHand.remove(card); // La quitamos de la lista
                card.setPlayer(null); // La desrelacionamos con la mano
                abilityService.saveAbilityCardInGame(card); // Guardamos los cambios en la carta

                currentPlayer.setAbilityHand(currentAbilityHand); // Reasignamos la mano al jugador
                playerService.savePlayer(currentPlayer); // Guardamos los cambios

            }else{

                card.setTurn(turn); // Relaciona con el turno
                cards_used.add(card); // Añadir a la lista de cartas usadas usadas este turno
                card.setPlayerDiscard(turn.getPlayer()); // Al descarte
                card.setPlayer(null); // Fuera de la mano

                turn.setCardsPlayed(cards_used); // Setear la lista de cartas usadas este turno

                turnService.save(turn); // Guardo los cambios en el turno
                abilityService.saveAbilityCardInGame(card); // // Guardo los cambios de LA CARTA

            }
        }
    }
    @Transactional
    // Terminar prematuramente el turno
    public void endAttack(Player player, Turn turn) {
        List<AbilityCardInGame> void_list = new ArrayList<AbilityCardInGame>();
        void_list.clear(); //Lista vacía con la que limpiar las cartas de los EnemyInGame
        List<EnemyInGame> field = turn.getGame().getMonsterField(); // Cojo los enemigos
        List<AbilityType> damage_nullifiers = Arrays.asList(AbilityType.ESCUDO, AbilityType.DISPARO_GELIDO,AbilityType.ENGANAR, AbilityType.CAPA_ELFICA); //Anuladores de daño
        int damage_taken = 0; // Aquí guardo el daño a sufrir
        for (EnemyInGame e:field){
            for(AbilityCardInGame a: e.getCardsPlayed()){
                a.setEnemyInGame(null);
                abilityService.saveAbilityCardInGame(a); //Cancelamos las realciones con el enemigo que tuvieran las cartas que se le lanzaron con este script
                }
            }
        int magic_tax = 0; //Daño que me restaré si el enemigo me hace daño y tiene la condión de mágico siendo yo el héroe mago
        for (EnemyInGame e:field){
            if(e.getEnemy().getCondition() != null && (player.getHero().equals(HeroType.MAGO_FEMENINO) || player.getHero().equals(HeroType.MAGO_MASCULINO))){ //Compruebo el heroe mágico
                if(e.getEnemy().getCondition().equals(ConditionType.MAGO_1)){
                    magic_tax--;
                    }
                if(e.getEnemy().getCondition().equals(ConditionType.MAGO_2)){
                    magic_tax -= 2;
                    }
                }
            List<AbilityType> lista_tipos = e.getCardsPlayed().stream().map(x->x.getAbilityCard().getAbilityType()).collect(Collectors.toList()); // Ver si le han usado algun anulador de daño
            if(!lista_tipos.stream().anyMatch(damage_nullifiers.stream().collect(Collectors.toSet())::contains)){
                damage_taken = damage_taken + e.getEnemy().getEndurance() - e.getWounds() + magic_tax;
                e.setCardsPlayed(void_list);
                enemyService.saveEnemyInGame(e);
                } // Voy sumando y acumulando
            }
        if (turn.getCardsPlayed().stream().filter(x->x.getAbilityCard().getAbilityType().equals(AbilityType.AURA_PROTECTORA)).count() > 0){
            loseCards(player, field.size());
            turnService.newTurn(turn.getGame(), player, PhaseType.MERCADO);
            
        }else{
            if (damage_taken>=1){ // Si el daño es negativo no se quita
                loseCards(player, damage_taken); //Pierdo cartas igual al número de daños sufridos
            }
            turnService.newTurn(turn.getGame(), player, PhaseType.MERCADO); // Cambio de fase
        }

    }
    @Transactional
	public void playAbilityCard(Turn turn, AbilityCardInGame card, EnemyInGame enemy, Integer currentGameId){
		AbilityType card_type = card.getAbilityCard().getAbilityType(); // Tipo de carta usada
		// HAY QUE PONER UN BREAK; AL FINAL DE CADA UNO
        // En las cartas de mercado hay que mirar la segunda proficiencia del héroe para ver si hay que restar
        // Checkear si el enemigo tiene reducción al daño en las cartas mágicas

        Player current_player = turn.getPlayer(); // Jugador actual
        List<AbilityCardInGame> turn_cards = turn.getCardsPlayed(); // Las cartas que se han jugado este turno
        List<AbilityCardInGame> enemy_cards = null;
        if(!enemy.equals(null)){
            enemy_cards = enemy.getCardsPlayed(); // Las cartas que se jugaron sobre el enemigo este turno
        }
		List<AbilityCardInGame> mazo_actual = current_player.getAbilityPile(); // Las cartas de mi mazo actualmente
        int card_damage = card.getAbilityCard().getDamage(); // Daño de la carta usada
        int plain_add_dmg = (int) turn_cards.stream().filter(x->x.getAbilityCard().getAbilityType().equals(AbilityType.PIEDRA_DE_AMOLAR)).count(); // Cuento las piedras de amolar para sumar daño
        int total_damage = card_damage + plain_add_dmg; // Daño total tras la suma
        int bonus = 0;

		switch (card_type) {
			case COMPANERO_LOBO: {
                // Daño 2, Previenes 2 de Daño --Fin--
				damageEnemy(current_player, enemy, card, card_damage + plain_add_dmg, 0);
				reduceDamage(turn, 2);
                break;
            }
			case DISPARO_CERTERO: {// Daño 3, Pierdes 1 cartas, Finalizas tu ataque --Fin--
				damageEnemy(current_player, enemy, card, total_damage, 0);
				loseCards(current_player, 1);
                endAttack(current_player, turn);
                break;
            }
			case DISPARO_RAPIDO: {// Daño 1, Roba 1 si es "Disparo rápido" úsala, sino ponla al fondo del mazo de Habilidad
                for(AbilityCardInGame c:mazo_actual){
                    if(c.getAbilityCard().getAbilityType().equals(AbilityType.DISPARO_RAPIDO)){
                        bonus++;
                    }else{
                        break;
                    }
                drawCards(current_player, bonus);
                for(int i = 0; i < bonus; i++)
                    damageEnemy(current_player, enemy, card, total_damage, 0);
                }
                // Le pongo un pin a esto y luego vuelvo la clave esta en el robo y descarte
                break;
            }

			case EN_LA_DIANA: {// Daño 4, Gana 1 de Gloria, Pierdes 1 carta --Fin--
                damageEnemy(current_player, enemy, card, total_damage, 0);
                current_player.setGlory(current_player.getGlory() + 1);
                playerService.savePlayer(current_player);
                loseCards(current_player, 1);
                break;
            }

			case LLUVIA_DE_FLECHAS: {// Daño 2, Esta carta daña a 2 enemigos y al héroe con menos heridas, en empate tú eliges
            //Le pongo un pin luego volvemos
                break;
            }
			case ATAQUE_BRUTAL: {// Daño 3, Pierdes 1 carta --Fin--
                damageEnemy(current_player, enemy, card, total_damage, 0);
                loseCards(current_player, 1);
                break;
            }

			case CARGA_CON_ESCUDO: {// Daño 2, Previenes 2 de Daño --Fin--
                damageEnemy(current_player, enemy, card, total_damage, 0);
                reduceDamage(turn, 2);
                break;
            }

			case DOBLE_ESPADAZO: {// Daño 2, Pierdes 1 carta --Fin--
                damageEnemy(current_player, enemy, card, total_damage, 0);
                loseCards(current_player, 1);
                break;
            }

			case ESPADAZO: {// Daño 1, si el primer "Espadazo" que juegas Roba 1 --Fin--
                damageEnemy(current_player, enemy, card, total_damage, 0);
                if(turn_cards.stream().filter(x -> x.getAbilityCard().getAbilityType().equals(card.getAbilityCard().getAbilityType())).count() == 0) {
                    drawCards(current_player, 1);
                }
                break;
            
            }
			case TODO_O_NADA: {// Daño 1, Roba 1 carta y súmale su daño a esta carta, Recupera la carta que robaste --Fin--
                AbilityCardInGame top_card = mazo_actual.get(0); //Carta del Top del Mazo
                mazo_actual.remove(top_card); 
                mazo_actual.add(top_card); // Esto la manda para abajo
                current_player.setAbilityPile(mazo_actual);
                playerService.savePlayer(current_player); // Guardo los cambios en la pila
                damageEnemy(current_player, enemy, card, total_damage + top_card.getAbilityCard().getDamage(), 0);
                break;
            }

			case DISPARO_GELIDO: {// Daño 1, El enemigo afectado no causa daño este turno, Roba 1 --Fin--
                for(AbilityCardInGame c:enemy.getCardsPlayed()){
                    if(c.getAbilityCard().getAbilityType().equals(AbilityType.FLECHA_CORROSIVA)){
                        bonus++;
                    }
                }
                damageEnemy(current_player, enemy, card, total_damage, 0);
                drawCards(current_player, 1);
                break;
            }

			case FLECHA_CORROSIVA: {// Daño 1, Las siguientes cartas que dañen a este enemigo le hacen 1 más de daño, Pierdes 1 carta --Fin--
                for(AbilityCardInGame c:enemy.getCardsPlayed()){
                    if(c.getAbilityCard().getAbilityType().equals(AbilityType.FLECHA_CORROSIVA)){
                        bonus++;
                    }
                }
                damageEnemy(current_player, enemy, card, total_damage, 0);
                loseCards(current_player, 1);
                break;
            }

			case GOLPE_DE_BASTON: {// Daño 1, Si no es el primer "Golpe de bastón" usado contra este enemigo en lugar de 1 esta carta hace 2 de daño
                for(AbilityCardInGame c:enemy.getCardsPlayed()){
                    if(c.getAbilityCard().getAbilityType().equals(AbilityType.FLECHA_CORROSIVA)){
                        bonus++;
                    }
                }
                if(turn_cards.stream().filter(x->x.getAbilityCard().getAbilityType().equals(card.getAbilityCard().getAbilityType())).count() >= 1){
                    bonus++;
                }
                damageEnemy(current_player, enemy, card, total_damage, 0);
                break;
            }

			case PROYECTIL_IGNEO: {// Daño 2, Gana 1 de Gloria --Fin--
                for(AbilityCardInGame c:enemy.getCardsPlayed()){
                    if(c.getAbilityCard().getAbilityType().equals(AbilityType.FLECHA_CORROSIVA)){
                        bonus++;
                    }
                }
                damageEnemy(current_player, enemy, card, total_damage, 0);
                current_player.setGlory(current_player.getGlory() + 1);
                playerService.savePlayer(current_player);
                break;
            }

			case TORRENTE_DE_LUZ: {// Daño 2, Todos menos tú recuperan 2, Ganas 1 de Gloria --Fin--
                
                for(AbilityCardInGame c:enemy.getCardsPlayed()){
                    if(c.getAbilityCard().getAbilityType().equals(AbilityType.FLECHA_CORROSIVA)){
                        bonus++;
                    }
                }
                if(total_damage+bonus<0){
                    total_damage = 0;
                }
                damageEnemy(current_player, enemy, card, total_damage, 0);
                List<Player> not_you = turn.getGame().getPlayer();
                for (Player player:not_you){
                    if(!current_player.equals(player)){
                        regainCards(player, 2);
                    }
                }
                current_player.setGlory(current_player.getGlory() + 1);
                playerService.savePlayer(current_player);
                break;
            }

			case AL_CORAZON: {// Daño 4, Si derrotas un enemigo con esto gana 1 Moneda si el primer "Al Corazón" del turno, Pierdes 1 carta --Fin--
                if(turn_cards.stream().filter(x->x.getAbilityCard().getAbilityType().equals(card.getAbilityCard().getAbilityType())).count() == 0 && 
                    ((enemy.getEnemy().getEndurance() - enemy.getWounds()) <= total_damage)){
                    bonus++;
                }
                damageEnemy(current_player, enemy, card, total_damage, bonus);
                loseCards(current_player, 1);
                break;
            }

			case ATAQUE_FURTIVO: {// Daño 2, Si derrotas un enemigo con esto gana 1 Moneda si el primer "Ataque Furtivo" del turno --Fin--
                if(turn_cards.stream().filter(x->x.getAbilityCard().getAbilityType().equals(card.getAbilityCard().getAbilityType())).count() == 0 && 
                    ((enemy.getEnemy().getEndurance() - enemy.getWounds()) <= total_damage)){
                    bonus++;
                }
                damageEnemy(current_player, enemy, card, total_damage, bonus);
                break;
            }

			case BALLESTA_PRECISA: {// Daño 2, Si ya usaste "Ballesta precisa" contra ese enemigo hace 1 punto más de daño --Fin--
                if(enemy_cards.stream().filter(x->x.getAbilityCard().getAbilityType().equals(card.getAbilityCard().getAbilityType())).count() >= 1){
                    bonus++;
                }
                damageEnemy(current_player, enemy, card, total_damage + bonus, 0);
                break;
            }

			case EN_LAS_SOMBRAS: {// Daño 1, Previenes 2 de Daño --Fin--
                damageEnemy(current_player, enemy, card, total_damage, 0);
                reduceDamage(turn, 2);
                break;
            }

			case DAGA_ELFICA: {//Daño 2, Coste 3, Si el héroe tiene como Proficiency "Pericia" se recupera tras jugarla, PROFICIENCIAS:  Distancia, Pericia, Melee
                damageEnemy(current_player, enemy, card, total_damage, 0);
                break;
            }

			case ALABARDA_ORCA: {//Daño 4, Coste 5, PROFICIENCIAS: Melee
            if(current_player.getSecondProfiency().equals(Profiency.MELEE)){
                bonus--;
            }
                damageEnemy(current_player, enemy, card, total_damage + bonus, 0);
                break;
            }  

			case ARCO_COMPUESTO: {//Daño 4, Coste 5, PROFICIENCIAS: Distancia
            if(current_player.getSecondProfiency().equals(Profiency.DISTANCIA)){
                bonus--;
            }
                damageEnemy(current_player, enemy, card, total_damage, 0);
                break;
            }

			///// Hacen target pero no daño
			case SUPERVIVENCIA: {// Daño 0, Cambia 1 enemigo por el siguiente en el mazo de Horda
                break;
            }
			case ESCUDO: {// Previenes el daño de un enemigo, Finalizas tu ataque --Fin--
                endAttack(current_player, turn);
                break;
            }

			case ENGANAR: {// Daño 0, Cuesta 2 monedas, El enemigo elegido no hace daño este turno --Fin--
                if(current_player.getGold()<2){
                    break;
                }else{
                    registerCardUsage(turn, enemy, card);
                    break;
                }
            }
			case CAPA_ELFICA: {// Daño 0, Coste 3, El enemigo seleccionado no hace daño este turno, PROFICIENCIAS : Distancia, Magia --Fin--
                break;
            }

			///// No requieren de obejetivo
			case RECOGER_FLECHAS: {// Daño 0, Recupera un "Disparo Rápido", Baraja tu mazo de Habildades, Gana 1 moneda --Fin--
                AbilityCardInGame disparo = current_player.getDiscardPile().stream().filter(x->x.getAbilityCard().getAbilityType().equals(AbilityType.DISPARO_RAPIDO)).findFirst().get();
                List<AbilityCardInGame> descarte = current_player.getDiscardPile();
                descarte.remove(disparo);
                descarte.add(0, disparo);
                current_player.setDiscardPile(descarte);
                playerService.savePlayer(current_player);
                regainCards(current_player, 1);
                List<AbilityCardInGame> mazo_nuevo = current_player.getAbilityPile();
                Collections.shuffle(mazo_nuevo);
                current_player.setAbilityPile(descarte);
                current_player.setGold(current_player.getGold() + 1);
                playerService.savePlayer(current_player);
                break;
            }

			case PASO_ATRAS: {// Daño 0, Roba 2 --Fin--
                drawCards(current_player, 2);
                break;
            }

			case VOZ_DE_ALIENTO: {// Todos Recuperan 2 cartas, Roba 1 carta y gana 1 de Gloria
                List<Player> players = turn.getGame().getPlayer();
                for (Player player:players){
                        regainCards(player, 2);
                }
                current_player.setGlory(current_player.getGlory() + 1);
                playerService.savePlayer(current_player);
                drawCards(current_player, 1);
                break;
            }

			case AURA_PROTECTORA: {//Daño 0, Cancela el daño del próximo ataque sufrido, Pierdes X cartas donde X es el número de enemigos en el campo
                break;
            }

			case BOLA_DE_FUEGO: {//Daño 2, Daña a todos los enemigos, El resto de héroes sufren 1 de Daño
                List<EnemyInGame> field = findById(currentGameId).get().getMonsterField();
                for (EnemyInGame e:field){
                    bonus = 0;
                    for(AbilityCardInGame c:e.getCardsPlayed()){
                        if(c.getAbilityCard().getAbilityType().equals(AbilityType.FLECHA_CORROSIVA)){
                            bonus++;
                        }
                    }
                    damageEnemy(current_player, e, card, total_damage + bonus, 0); 
                }

                List<Player> jugadores = findById(currentGameId).get().getPlayer();
                for(Player p:jugadores){
                    if(!p.equals(current_player)){
                        p.setWounds(p.getWounds()-1);
                        playerService.savePlayer(p);
                    }
                }
                break;
            }

			case ORBE_CURATIVO: {// Daño 0, Todos Recuperan 2 cartas, Eliminas 1 herida de tu héroe, Elimina esta carta del juego --Fin--
                
                List<Player> players = findById(currentGameId).get().getPlayer();
                for (Player player:players){
                        regainCards(player, 2);
                }
                current_player.setWounds(current_player.getWounds() + 1);
                playerService.savePlayer(current_player);
                break;
            }

			case RECONSTITUCION: {// Daño 0, Roba 1 carta, Recupera 2 cartas --Fin--
                drawCards(current_player, 1);
                regainCards(current_player, 2);
                break;
            }

			case ROBAR_BOLSILLOS: {//Daño 0, Roba 1 moneda a cada héroe
            List<Player> players = turn.getGame().getPlayer();
                for (Player p:players){
                    if(!(p.getGold() > 1)){
                        p.setGold(p.getGold() - 1);
                        playerService.savePlayer(p);
                        current_player.setGold(current_player.getGold() + 1);
                        playerService.savePlayer(current_player);
                    }
                }
                break;
            }

			case SAQUEO: { //Daño 0, Gana 1 moneda por cada Enemigo en el campo, Ganas 1 de Gloria --Fin--
                List<EnemyInGame> field = findById(currentGameId).get().getMonsterField();
                current_player.setGold(current_player.getGold() + field.size());
                current_player.setGlory(current_player.getGlory() + 1);
                playerService.savePlayer(current_player);
                break;
            }

			case TRAMPA: {// Daño 0, Al resolver el ataque de la horda derrotas al enemigo de mayor Fortaleza pero su botín se anula
                break;

            }
			case POCION_CURATIVA: {//Daño 0, Coste 8, Retira una herida de tu héroe, Eliminala del juego --Fin--
                current_player.setWounds(current_player.getWounds()+1);
                break;
            }

			case PIEDRA_DE_AMOLAR: {// Daño 0. Coste 4, Todas tus cartas hacen 1 más de daño este turno si hacían al menos 1 de Daño
                break;
            }

			case VIAL_DE_CONJURACION: {// Daño 0, Coste 5, Busca una carta de tu pila de Desgaste y ponla en tu mano
                break;
            }
                //Se ha implementado por Martín

			case ELIXIR_DE_CONCENTRACION: {// Daño 0, Coste 3, Roba 3 cartas --Fin--
                drawCards(current_player, 3);
                break;
            }

			case ARMADURA_DE_PLACAS: {//Daño 0, Coste 4, Recuperas 4 cartas, PROFICIENCIAS: Melee --Fin--
                regainCards(current_player, 4);
		    }
        }

        if(card.getAbilityCard().getAbilityType().equals(AbilityType.ENGANAR)){
            
        }else{
          registerCardUsage(turn, enemy, card); // Registra el uso de la carta
        }
    }
}
