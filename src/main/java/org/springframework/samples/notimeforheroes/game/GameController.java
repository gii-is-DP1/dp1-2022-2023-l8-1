package org.springframework.samples.notimeforheroes.game;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.player.Player;
import org.springframework.samples.notimeforheroes.player.PlayerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) auth.getPrincipal();
        List<Game> gl = service.gameList();
        boolean userIsAnActivePlayer = false;
        for (int i = 0; i < gl.size(); i+=1) { //para cada game
            Game g = gl.get(i);
            if(!g.getState().equals(GameState.TERMINADO)) { //si el game no terminó
                List<Player> pl = g.getPlayers(); //pl: lista de players en el game i (entre 2 y 4 players)
                for(int j = 0; j < pl.size(); j+=1) {//para cada player dentro del game i
                    Player p = pl.get(j);
                    if(p.getUser().getUsername().equals(currentUser.getUsername())) { //si el usuario asociado al player es el mismo que el currentUser
                        j = pl.size();
                        i = gl.size();//finalizar los 2 bucles
                        userIsAnActivePlayer = true;
                        //aqui iria el mensaje
                    }
                }
            }
        }


        /*boolean userHasAnUnfinishedGame = false;
        for (int i = 0; i < gl.size(); i+=1) {
            Game g = gl.get(i);
            if(!g.getState().equals(GameState.TERMINADO) && g.getUsername().equals(username) && !currentUser.getAuthorities().toString().contains("admin")) {
                i = gl.size();
                userHasAnUnfinishedGame = true;
            }
        }*/
 
		if(br.hasErrors() || game.getMinPlayers() > game.getMaxPlayers() || userIsAnActivePlayer  ) { 
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

}
