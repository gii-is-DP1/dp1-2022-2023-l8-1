package org.springframework.samples.notimeforheroes.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.player.Player;
import org.springframework.samples.notimeforheroes.player.PlayerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
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


    private final GameService service;
    @Autowired
    private final PlayerService playerService;

    @Autowired
    public GameController(GameService gameService, PlayerService playerService){
        this.service = gameService;
        this.playerService=playerService;
    }


    @GetMapping("/")
    public ModelAndView showGameList(){
        ModelAndView mav = new ModelAndView(VIEW_GAME_LIST);
        mav.addObject("games", service.gameList());
        return mav;
    }

    @GetMapping("/{gameId}/lobby")
    public ModelAndView showLobby(@PathVariable("gameId") int gameId){
        ModelAndView mav = new ModelAndView(VIEW_GAME_LOBBY);
        List<Player> players = service.showPlayersInGame(gameId);
        
        mav.addObject("players", players);
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

    @GetMapping(value = "/board")
    public ModelAndView showBoard(){
        ModelAndView mav = new ModelAndView("games/board");
        return mav;
    }

}
