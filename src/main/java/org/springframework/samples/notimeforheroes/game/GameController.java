package org.springframework.samples.notimeforheroes.game;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
    public GameController(GameService gameService){
        this.service = gameService;
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
    public ModelAndView createGame(@Valid Game game, BindingResult br){
        ModelAndView mav = null;
		if(br.hasErrors()){
			mav = new ModelAndView(VIEW_GAME_NEW);
			mav.addAllObjects(br.getModel());
		}else{
			service.createGame(game);
			mav = showGameList();
			mav.addObject("message", "User saved correctly");
		}

		return mav;
    }

    
}