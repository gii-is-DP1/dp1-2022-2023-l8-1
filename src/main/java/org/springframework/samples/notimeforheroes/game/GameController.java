package org.springframework.samples.notimeforheroes.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.card.ability.AbilityCardInGame;
import org.springframework.samples.notimeforheroes.card.ability.AbilityService;
import org.springframework.samples.notimeforheroes.card.market.MarketCardInGame;
import org.springframework.samples.notimeforheroes.friends.Friends;
import org.springframework.samples.notimeforheroes.friends.FriendsService;
import org.springframework.samples.notimeforheroes.player.HeroType;
import org.springframework.samples.notimeforheroes.player.Player;
import org.springframework.samples.notimeforheroes.player.PlayerService;
import org.springframework.samples.notimeforheroes.turn.PhaseType;
import org.springframework.samples.notimeforheroes.turn.Turn;
import org.springframework.samples.notimeforheroes.turn.TurnService;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/games")
public class GameController {

    //Vistas de jsp a usar
    private static final String VIEW_GAME_LIST = "games/showGameList";
    private static final String VIEW_GAME_NEW = "games/createGame";
    private static final String VIEW_GAME_LOBBY = "games/showGameLobby";

    private static final String VIEW_ENEMIES_ACTIVES = "games/viewEnemyActives";
    private static final String VIEW_MARKET = "games/viewMarket";
    private static final String VIEW_CHOOSE_LEADER = "games/chooseLeader";

    //Repositorios y servicios como variables y posterior asociación al controlador
    private final FriendsService friendsService;

    private final GameService service;

    private final UserService userService;
    private final TurnService turnService;

    private final AbilityService abilityService;

    @Autowired
    private final PlayerService playerService;

    @Autowired
    public GameController(GameService gameService, PlayerService playerService, FriendsService fs, UserService uService, TurnService tService, AbilityService aService){
        this.service = gameService;
        this.playerService = playerService;
        this.friendsService = fs;
        this.userService = uService;
        this.turnService = tService;
        this.abilityService = aService;
    }

    // @GetMapping('/')

    //controlador para vista de todos los games
    @GetMapping("/")
    public ModelAndView showGameList(){
        ModelAndView mav = new ModelAndView(VIEW_GAME_LIST);
        mav.addObject("games", service.gameList());
        return mav;
    }

    // Builder para la partida y comando para las cartas

    @GetMapping("/{gameId}/lobby")
    public ModelAndView showLobby(@PathVariable("gameId") int gameId){
        ModelAndView mav = new ModelAndView(VIEW_GAME_LOBBY);
        List<Player> players = service.showPlayersInGame(gameId);
        Collection<Friends> friends = friendsService.findFriends();
        ArrayList<HeroType> heroTypes = new ArrayList<>();
        String heroeAMostrar = "";
        for(HeroType h : HeroType.values()){
            if( h != HeroType.SIN_HEROE){
                heroTypes.add(h);
            }
        }

        for(Player player : players){
            if(player.getHero() != null){
                String[] heroe = player.getHero().toString().split("_"); // Accedo al valor del heroe quitanto el FEMENINO/MASCULINO

                heroTypes.removeIf(e -> e.toString().contains(heroe[0])); // Elimino los heroes que se hayan escogido de la lista de heroes

                heroeAMostrar = heroe[0] +" "+ heroe[1];
            }
        }

        mav.addObject("heroeAMostrar", heroeAMostrar);
        mav.addObject("heroTypes", heroTypes);
        mav.addObject("friends", friends);
        mav.addObject("players", players);
        return mav;
    }

    //controlador y vista para la selección de heroe
    @GetMapping("/{gameId}/lobby/selectHero/{heroType}")
    public String selectHero(@PathVariable("gameId") int gameId, ModelMap modelMap, @PathVariable("heroType") HeroType heroType) {


            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = userService.getByUsername(auth.getName());

            modelMap.addAttribute("gameId", gameId);
            modelMap.addAttribute("heroType", heroType);

            List<Player> players = service.findById(gameId).get().getPlayer();
            for(Player p : players){
                if(p.getUser().getUsername() == currentUser.getUsername()){
                    p.setHero(heroType);
                    abilityService.addAbilityCards(p, heroType);
                    playerService.savePlayer(p);
                }
            }
                
            return "redirect:/games/"+gameId+"/lobby";
        }
    
    //controlador y vista para la elección de un lider de partida dependiendo de la puntuación de cartas
    @GetMapping("{gameId}/chooseLeader")
    public ModelAndView showPuja(@PathVariable("gameId") int gameId){
        ModelAndView mav = new ModelAndView(VIEW_CHOOSE_LEADER);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getByUsername(auth.getName());
        Player currentPlayer = new Player();
      
        // Establecer la partida en escoger líder
        Game currentGame = service.findById(gameId).get();
        currentGame.setState(GameState.ESCOGER_LIDER);
        service.saveGame(currentGame);
        
        //Una vez se llega aquí suponemos que ya están en la partida todos los jugadores que van a jugar
        //Poblamos el juego de enemigos respecto el número de jugadores
        if(currentGame.getMonsterPile().size()==0 && currentGame.getState().toString()=="ESCOGER_LIDER") { //comprobar que esté aquí para que no se pueda poblar antes o después
        	service.insertMonsterPile(currentGame.getPlayer().size());
        }
        
        List<Player> players = service.findById(gameId).get().getPlayer();
        for(Player p : players){
            if(p.getUser().getUsername() == currentUser.getUsername()){
                currentPlayer = p;
                abilityService.addStartingHand(p);
                playerService.savePlayer(p);
            }
        }
        mav.addObject("cardInGames", currentPlayer.getAbilityHand());
        mav.addObject("game", currentGame);
        mav.addObject("players", players);
        return mav;
    }

    //controlador y vista para la puja de cartas para la puntuación de elección de lider
    @GetMapping("{gameId}/chooseLeader/{cardId}")
    public ModelAndView pujarCarta(@PathVariable("gameId") int gameId, @PathVariable("cardId") int cardId){
        ModelAndView mav = new ModelAndView(VIEW_CHOOSE_LEADER);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getByUsername(auth.getName());
        Player currentPlayer = new Player();
        Game currentGame = service.findById(gameId).get();
        
        List<Player> players = service.findById(gameId).get().getPlayer();
        for(Player p : players){
            if(p.getUser().getUsername() == currentUser.getUsername()){
                currentPlayer = p;
                playerService.pujarCarta(p, cardId);
                // cartasPuja = p.getCartasPuja();
            }
        }
        System.out.println("=================================Cartas en la puja " + currentPlayer.getCartasPuja());
        System.out.println("=================================Cartas en la mano " + currentPlayer.getAbilityHand());
        System.out.println("=================================Jugador actual " + currentPlayer.getUser().getUsername());
        // System.out.println("=================================Errores " + currentPlayer.getUser().getUsername());



        mav.addObject("cartasPuja", currentPlayer.getCartasPuja());
        mav.addObject("cardInGames", currentPlayer.getAbilityHand());
        mav.addObject("players", players);
        mav.addObject("game", currentGame);
        mav.addObject("currentPlayer", currentPlayer);

        return mav;

    }

    //controlador para la comparación de puntuación de la puja de cartas
    @GetMapping("{gameId}/chooseLeader/compare")
    public ModelAndView compareCardsToSelectLeader(@PathVariable("gameId") int gameId){
        ModelAndView mav = new ModelAndView(VIEW_CHOOSE_LEADER);

        Player bestPlayerBet = service.compareBet(gameId);

        int bet = 0;

        for(AbilityCardInGame card : bestPlayerBet.getCartasPuja()){
            bet += card.getAbilityCard().getDamage();
        }
        Turn newTurn = new Turn();
        Game currentGame = service.findById(gameId).get();
        currentGame.setState(GameState.EN_CURSO);
        newTurn.setGame(currentGame);
        newTurn.setPlayer(bestPlayerBet);
        newTurn.setType(PhaseType.ATAQUE);
        turnService.save(newTurn);
        service.saveGame(currentGame);
        

        mav.addObject("bestPlayerBet", bestPlayerBet);
        mav.addObject("bestBet", bet);
        mav.addObject("game", currentGame);



        
        return mav;
    }

    
    //controlador vista para la creación de game

    @GetMapping("/new")
    public ModelAndView createGameView(){
        ModelAndView mav = new ModelAndView(VIEW_GAME_NEW);
        
        Game game = new Game();

        mav.addObject("game", game);
        return mav;
    }

    //Controlador post para la creación de un nuevo juego
    @PostMapping("/new")
    public String createGame(@Valid Game game, BindingResult br){
        ModelAndView mav = null;
		if(br.hasErrors() || (game.getMinPlayers() > game.getMaxPlayers())){ //el juego no se crea
			mav = new ModelAndView(VIEW_GAME_NEW);
			mav.addAllObjects(br.getModel());
		}else{
			service.createGame(game);
			mav = showGameList();
			mav.addObject("message", "Game saved correctly");
		}

		return "redirect:/games/";
    }

    //Controlador vista para los enemigos asociados a un juego
    @GetMapping(value = "/monsterField/{gameId}")
    public ModelAndView showEnemiesList(@PathVariable("gameId") int gameId){
        ModelAndView mav = new ModelAndView(VIEW_ENEMIES_ACTIVES);
        Game game = service.findById(gameId).get();
        mav.addObject("game", game);

        return mav;
    }

    //Controlador vista para el mercado asociado a un juego
    @GetMapping(value = "/market/{gameId}")
    public ModelAndView showmarketList(@PathVariable("gameId") int gameId){
        ModelAndView mav = new ModelAndView(VIEW_MARKET);
        Game game = service.findById(gameId).get();
        mav.addObject("game", game);

        return mav;
    }

    //Controlador vista para el tablero de un juego
    @GetMapping(value = "/board/{gameId}")
    public ModelAndView showBoard(@PathVariable("gameId") int gameId, HttpServletResponse response){
        // response.addHeader("Refresh", "1"); // Autorefresco
    	
        ModelAndView mav = new ModelAndView("games/board");
        Game game =service.findById(gameId).get();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String currentUserName = auth.getName();
	    User currentUser = userService.findByUsername(currentUserName);
	    List<Player> players = game.getPlayer();
	    Player player = players.stream().filter(x->x.getUser().equals(currentUser)).findFirst().get();
	    
        Turn currentTurn = game.getTurn().get(game.getTurn().size()-1); // Accedo al ultimo elemento de la lista de turnos
        
        Boolean isMyTurn = false;
        Player myPlayer = service.getCurrentPlayer(currentUser, gameId);

        if(currentTurn.getPlayer() == myPlayer){ // Esto se hace para saber si es mi turno y poder pasar de turno.
            isMyTurn = true;
        }

        PhaseType fase = currentTurn.getType();

        if(fase.equals(PhaseType.RESTABLECIMIENTO) && ((player.getAbilityHand().size() + Math.abs(player.getMarketHand().size() - player.getMarketDiscardPile().size())) > 4)){
            mav.addObject("message","Debes de pasar de turno con 4 cartas");

        }
        
        
	    mav.addObject("game",game);
	    mav.addObject("player",player);
        mav.addObject("turn", currentTurn);
        mav.addObject("isMyTurn", isMyTurn);
        mav.addObject("fase", fase);

        return mav;
    }

    //controlador para pasar de turno o fase
    @GetMapping("/board/{gameId}/next")
    public String nextTurnOrPhase(@PathVariable("gameId") int gameId,  ModelMap modelMap){
        Game currentGame = service.findById(gameId).get();
        Turn currentTurn = currentGame.getTurn().get(currentGame.getTurn().size()-1);

        Player currentPlayerGaming = currentTurn.getPlayer();
        Player nextPlayerToGame = new Player();

        List<Player> players = service.showPlayersInGame(gameId);
        Integer acum = 0;
        for(Player player:players) {
            if(player.getWounds()>0) {
                acum += 1;

            }
        }
        if(acum == 0) {
            return "redirect:/games/{gameId}/finishGame";
        } else if(currentGame.getMonsterField().size()==0 && currentGame.getMonsterPile().size()==0) {
            return "redirect:/games/{gameId}/finishGame";
        }

        try{
            nextPlayerToGame = currentGame.getPlayer().get(currentGame.getPlayer().indexOf(currentPlayerGaming)+1);

        }catch (Exception e){
            nextPlayerToGame = currentGame.getPlayer().get(0);
        }
        
        if(currentTurn.getType() == PhaseType.ATAQUE){
            turnService.newTurn(currentGame, currentPlayerGaming, PhaseType.MERCADO);
        }else if(currentTurn.getType() == PhaseType.MERCADO){
            turnService.newTurn(currentGame, currentPlayerGaming, PhaseType.RESTABLECIMIENTO);
        }else{
        	service.resupplyEnemies(gameId);
            if((currentPlayerGaming.getAbilityHand().size() + Math.abs(currentPlayerGaming.getMarketHand().size() - currentPlayerGaming.getMarketDiscardPile().size())) <= 4){
                turnService.newTurn(currentGame, nextPlayerToGame, PhaseType.ATAQUE);
            }
        }

        return "redirect:/games/board/"+gameId;
    }


    //controlador para la acción de evasión
    @GetMapping("/board/{gameId}/evasion")
    public String userEvasion(@PathVariable("gameId") int gameId,  ModelMap modelMap){
        Game currentGame = service.findById(gameId).get();
        Turn currentTurn = currentGame.getTurn().get(currentGame.getTurn().size()-1);

        Player currentPlayerGaming = currentTurn.getPlayer();
        Player nextPlayerToGame = new Player();

        try{
            nextPlayerToGame = currentGame.getPlayer().get(currentGame.getPlayer().indexOf(currentPlayerGaming)+1);

        }catch (Exception e){
            nextPlayerToGame = currentGame.getPlayer().get(0);
        }

        if(currentPlayerGaming.getAbilityHand().size() >1){ // Al hacer la evasión se descartan 2 cartas
            int cardId1 = currentPlayerGaming.getAbilityHand().get(0).getId();
            int cardId2 = currentPlayerGaming.getAbilityHand().get(1).getId();
            service.discardAbilityCard(currentPlayerGaming.getUser(), gameId, cardId1);
            service.discardAbilityCard(currentPlayerGaming.getUser(), gameId, cardId2);
        }
        
        turnService.newTurn(currentGame, nextPlayerToGame, PhaseType.ATAQUE);
        
        return "redirect:/games/board/"+gameId;
    }

    //controlador para la compra de cartas de mercado
    @GetMapping("/board/{gameId}/buy/{marketCardId}")
    public String buyCard(@PathVariable("gameId") int gameId, @PathVariable("marketCardId") int marketCardId, ModelMap modelMap){
        // Game currentGame = service.findById(gameId).get();
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String currentUserName = auth.getName();
	    User currentUser = userService.findByUsername(currentUserName);

        service.buyCard(currentUser, gameId, marketCardId);

        return "redirect:/games/board/"+gameId;
    }

    @GetMapping("/board/{gameId}/discard/{abilityCardId}")
    public String discardAbilityCard(@PathVariable("gameId") int gameId, @PathVariable("abilityCardId") int cardId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String currentUserName = auth.getName();
	    User currentUser = userService.findByUsername(currentUserName);

        service.discardAbilityCard(currentUser, gameId, cardId);

        return "redirect:/games/board/"+gameId;
    }

    @GetMapping("/board/{gameId}/discardMarketCard/{marketCardId}")
    public String discardMarketCard(@PathVariable("gameId") int gameId, @PathVariable("marketCardId") int marketCardId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String currentUserName = auth.getName();
	    User currentUser = userService.findByUsername(currentUserName);

        service.discardMarketCard(currentUser, gameId, marketCardId);

        return "redirect:/games/board/"+gameId;
    }

    @GetMapping("/board/{gameId}/stealCard")
    public String stealCard(@PathVariable("gameId") int gameId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String currentUserName = auth.getName();
	    User currentUser = userService.findByUsername(currentUserName);

        service.stealCard(service.getCurrentPlayer(currentUser, gameId));

        return "redirect:/games/board/"+gameId;
    }

    @GetMapping("/{gameId}/finishGame")
    public ModelAndView finishGame(@PathVariable("gameId") int gameId) {
        Game currentGame = service.findById(gameId).get();

        List<Player> players = currentGame.getPlayer();
        Integer maxGlory = 0;
        
        Player winner = new Player();
        for(Player player:players) {
            player.setGlory(player.getGlory()+(int)(player.getGold()/3));
            if(player.getGlory()>maxGlory) {
                maxGlory = player.getGlory();
                winner = player;
            } else if (player.getGlory() == maxGlory) {
                if(winner.getWounds()<player.getWounds()) {
                    winner = player;
                }
            }
        }
        Integer acum = 0;
        for(Player player:players) {
            if(player.getWounds()>0) {
                acum += 1;

            }
        }
        if(acum == 0) {
            winner = null;
        }
        currentGame.setEndTime(new Date());
        currentGame.setState(GameState.TERMINADO);
        service.saveGame(currentGame);

        ModelAndView mav = new ModelAndView("games/gameFinished");
        Game game = service.findById(gameId).get();
        mav.addObject("game", game);
        mav.addObject("winner", winner);

        return mav;


    }

    @GetMapping("/board/{gameId}/showDiscards")
    public String showDiscards(@PathVariable("gameId") int gameId, ModelMap modelMap){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String currentUserName = auth.getName();
	    User currentUser = userService.findByUsername(currentUserName);

        Player currentPlayer = service.getCurrentPlayer(currentUser, gameId);

        List<AbilityCardInGame> discardPile = currentPlayer.getDiscardPile();
        List<MarketCardInGame> marketDiscardPile = currentPlayer.getMarketDiscardPile();

        modelMap.addAttribute("discardPile", discardPile);
        modelMap.addAttribute("marketDiscardPile", marketDiscardPile);
        modelMap.addAttribute("game", service.findById(gameId).get());

        return "games/showDiscards";
    }

    @GetMapping("/{gameId}/selectDiscard/{cardId}")
    public String selectDiscard(@PathVariable("gameId") int gameId,@PathVariable("cardId") int cardId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String currentUserName = auth.getName();
	    User currentUser = userService.findByUsername(currentUserName);

        Player currentPlayer = service.getCurrentPlayer(currentUser, gameId);

        service.recoverCard(cardId, currentPlayer);


        return "redirect:/games/board/"+gameId;

    }

    @GetMapping("/{gameId}/selectMarketDiscard/{marketCardId}")
    public String selectMarketDiscard(@PathVariable("gameId") int gameId, @PathVariable("marketCardId") int marketCardId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String currentUserName = auth.getName();
	    User currentUser = userService.findByUsername(currentUserName);

        Player currentPlayer = service.getCurrentPlayer(currentUser, gameId);

        service.recoverMarketCard(marketCardId, currentPlayer);


        return "redirect:/games/board/"+gameId;

    }

    @GetMapping("/{gameId}/roghkiller")
    public String roghkiller(@PathVariable("gameId") int gameId){

        Game currentGame = service.findById(gameId).get();
        service.roghkiller(currentGame);
        return "redirect:/games/board/"+gameId;
    }



}
