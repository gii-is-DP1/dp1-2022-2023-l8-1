package org.springframework.samples.notimeforheroes.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.card.ability.AbilityCardInGame;
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

    private static final String VIEW_GAME_LIST = "games/showGameList";
    private static final String VIEW_GAME_NEW = "games/createGame";
    private static final String VIEW_GAME_LOBBY = "games/showGameLobby";

    private static final String VIEW_ENEMIES_ACTIVES = "games/viewEnemyActives";
    private static final String VIEW_MARKET = "games/viewMarket";
    private static final String VIEW_CHOOSE_LEADER = "games/chooseLeader";


    private final FriendsService friendsService;

    private final GameService service;

    private final UserService userService;
    private final TurnService turnService;

    @Autowired
    private final PlayerService playerService;

    @Autowired
    public GameController(GameService gameService, PlayerService playerService, FriendsService fs, UserService uService, TurnService tService){
        this.service = gameService;
        this.playerService = playerService;
        this.friendsService = fs;
        this.userService = uService;
        this.turnService = tService;
    }

    // @GetMapping('/')


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
            heroTypes.add(h);
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
                    playerService.savePlayer(p);
                }
            }
                
            return "redirect:/games/"+gameId+"/lobby";
        }


    // @GetMapping("/{gameId}/start")
    // public ModelAndView startGame(@PathVariable("gameId") int gameId){
    //     ModelAndView mav = new ModelAndView(VIEW_GAME_LOBBY);
    //     Game game = service.findById(gameId).get();
    //     game.setState(GameState.ESCOGER_LIDER);
    //     service.saveGame(game);
    //     mav.addObject("message", "Partida iniciada");
    //     return mav;
    // }

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


        List<Player> players = service.findById(gameId).get().getPlayer();
        for(Player p : players){
            if(p.getUser().getUsername() == currentUser.getUsername()){
                currentPlayer = p;

            }
        }
        mav.addObject("cardInGames", currentPlayer.getAbilityHand());
        mav.addObject("game", currentGame);
        mav.addObject("players", players);
        return mav;
    }

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

    @GetMapping("{gameId}/chooseLeader/compare")
    public ModelAndView compareCardsToSelectLeader(@PathVariable("gameId") int gameId){
        ModelAndView mav = new ModelAndView(VIEW_CHOOSE_LEADER);

        Player bestPlayerBet = service.compareBet(gameId);

        int bet = 0;

        for(AbilityCardInGame card : bestPlayerBet.getCartasPuja()){
            bet += card.getAbilityCard().getDamage();
        }

        Game currentGame = service.findById(gameId).get();
        currentGame.setState(GameState.EN_CURSO);
        service.saveGame(currentGame);


        mav.addObject("bestPlayerBet", bestPlayerBet);
        mav.addObject("bestBet", bet);
        mav.addObject("game", currentGame);



        
        return mav;
    }

    


    @GetMapping("/new")
    public ModelAndView createGameView(){
        ModelAndView mav = new ModelAndView(VIEW_GAME_NEW);
        
        Game game = new Game();

        mav.addObject("game", game);
        return mav;
    }

    @PostMapping("/new")
    public String createGame(@Valid Game game, BindingResult br){
        ModelAndView mav = null;
		if(br.hasErrors() || (game.getMinPlayers() > game.getMaxPlayers())){ //el juego no se crea
			mav = new ModelAndView(VIEW_GAME_NEW);
			mav.addAllObjects(br.getModel());
		}else{
			service.createGame(game);
			service.insertMonsterPile();
			mav = showGameList();
			mav.addObject("message", "Game saved correctly");
		}

		return "redirect:/games/";
    }

    @GetMapping(value = "/monsterField/{gameId}")
    public ModelAndView showEnemiesList(@PathVariable("gameId") int gameId){
        ModelAndView mav = new ModelAndView(VIEW_ENEMIES_ACTIVES);
        Game game = service.findById(gameId).get();
        mav.addObject("game", game);

        return mav;
    }

    
    @GetMapping(value = "/market/{gameId}")
    public ModelAndView showmarketList(@PathVariable("gameId") int gameId){
        ModelAndView mav = new ModelAndView(VIEW_MARKET);
        Game game = service.findById(gameId).get();
        mav.addObject("game", game);

        return mav;
    }


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

        // turnService.newTurn(game, player, PhaseType.RESTABLECIMIENTO);

        
        Turn currentTurn = game.getTurn().get(game.getTurn().size()-1); // Accedo al ultimo elemento de la lista de turnos
        
        // System.out.println("Turno - "+turno + " de: "+player.getUser().getUsername());


        Boolean isMyTurn = false;
        Player myPlayer = service.getCurrentPlayer(currentUser, gameId);

        if(currentTurn.getPlayer() == myPlayer){ // Esto se hace para saber si es mi turno y poder pasar de turno.
            isMyTurn = true;
        }

        Boolean faseMercado = false;
        if(currentTurn.getType().equals(PhaseType.MERCADO)){
            faseMercado = true;
        }

	    
	    mav.addObject("game",game);
	    mav.addObject("player",player);
        mav.addObject("turn", currentTurn);
        mav.addObject("isMyTurn", isMyTurn);
        mav.addObject("faseMercado", faseMercado);

        return mav;
    }

    @GetMapping("/board/{gameId}/next")
    public String nextTurnOrPhase(@PathVariable("gameId") int gameId,  ModelMap modelMap){
        Game currentGame = service.findById(gameId).get();
        Turn currentTurn = currentGame.getTurn().get(currentGame.getTurn().size()-1);

        Player currentPlayerGaming = currentTurn.getPlayer();
        Player nextPlayerToGame = new Player();

        
        

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
            turnService.newTurn(currentGame, nextPlayerToGame, PhaseType.ATAQUE);
        }

        return "redirect:/games/board/"+gameId;
    }


    
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
        
        turnService.newTurn(currentGame, nextPlayerToGame, PhaseType.ATAQUE);
        
        return "redirect:/games/board/"+gameId;
    }


    @GetMapping("/board/{gameId}/buy/{marketCardId}")
    public String buyCard(@PathVariable("gameId") int gameId, @PathVariable("marketCardId") int marketCardId, ModelMap modelMap){
        Game currentGame = service.findById(gameId).get();
        Turn currentTurn = currentGame.getTurn().get(currentGame.getTurn().size()-1);







        return "";
    }


}
