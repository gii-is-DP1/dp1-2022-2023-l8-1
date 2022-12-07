package org.springframework.samples.notimeforheroes.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.friends.Friends;
import org.springframework.samples.notimeforheroes.friends.FriendsService;
import org.springframework.samples.notimeforheroes.player.HeroType;
import org.springframework.samples.notimeforheroes.player.Player;
import org.springframework.samples.notimeforheroes.player.PlayerService;
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

    private final FriendsService friendsService;

    private final GameService service;

    private final UserService userService;

    @Autowired
    private final PlayerService playerService;

    @Autowired
    public GameController(GameService gameService, PlayerService playerService, FriendsService fs, UserService uService){
        this.service = gameService;
        this.playerService = playerService;
        this.friendsService = fs;
        this.userService = uService;
    }


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


    @GetMapping("/{gameId}/start")
    public ModelAndView startGame(@PathVariable("gameId") int gameId){
        ModelAndView mav = new ModelAndView(VIEW_GAME_LOBBY);
        Game game = service.findById(gameId).get();
        game.setState(GameState.ESCOGER_LIDER);
        service.saveGame(game);
        mav.addObject("message", "Partida iniciada");
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
            //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            //User currentUser = (User) auth.getPrincipal();
            
			service.createGame(game);
            //game.setUser(currentUser);
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
    public ModelAndView showBoard(@PathVariable("gameId") int gameId){
        ModelAndView mav = new ModelAndView("games/board");
        Game game =service.findById(gameId).get();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String currentUserName = auth.getName();
	    User currentUser = userService.findByUsername(currentUserName);
	    List<Player> players = game.getPlayer();
	    Player player = players.stream().filter(x->x.getUser().equals(currentUser)).findFirst().get();
	    
	    mav.addObject("game",game);
	    mav.addObject("player",player);
        return mav;
    }

}
