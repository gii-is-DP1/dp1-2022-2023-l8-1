package org.springframework.samples.notimeforheroes.player;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
// @RequestMapping("")
public class PlayerController {

    private static final String VIEWS_PLAYER_LISTS = "admins/playerLists";
    private static final String VIEWS_PLAYERS_CREATE_FORM = "admins/createPlayerForm";
    private static final String VIEWS_PLAYER_CARDS_LIST = "players/cardsInHandList";


    private final PlayerService playerService;
    
    private final GameService gameService;
    
    private final UserService userService;

    @Autowired
    public PlayerController(PlayerService playerService, GameService gameService,UserService userService){
        this.playerService = playerService;
		this.gameService = gameService;
		this.userService = userService;
    }
    
        @GetMapping(value="/players/{playerId}")
        public ModelAndView showCardList(@PathVariable("playerId")int playerId) {
        ModelAndView model = new ModelAndView(VIEWS_PLAYER_CARDS_LIST);
        Player player = this.playerService.findPlayerById(playerId).get();
		model.addObject("player", player);
		return model;
        
    }

    @GetMapping("/admins/players")
    public ModelAndView showPlayerList(){
        ModelAndView mav = new ModelAndView(VIEWS_PLAYER_LISTS);
        mav.addObject("players", playerService.findPlayers());
        return mav;
    }

    @ModelAttribute("types")
	public Collection<HeroType> populateHeroType() {
		return List.of(HeroType.values());
	}

    @GetMapping(value = "/admins/createPlayerForm")
	public ModelAndView createPlayerView(){
		ModelAndView mav = new ModelAndView(VIEWS_PLAYERS_CREATE_FORM);
        Player player = new Player();
		mav.addObject("player", player);
		return mav;

	}

	@PostMapping(value = "/admins/createPlayerForm")
	public String savePlayer(@Valid Player player,BindingResult br ){
		ModelAndView mav = null;
		if(br.hasErrors()){
			mav = new ModelAndView(VIEWS_PLAYERS_CREATE_FORM);
			mav.addAllObjects(br.getModel());
		}else{
			playerService.savePlayer(player);
			mav = showPlayerList();
			mav.addObject("message", "User saved correctly");
		}

		return "redirect:/admins/players";
	}
	@GetMapping("/games/{gameId}/join")
	  public String joinGame(@PathVariable("gameId")int gameId) {//HAY QUE AÑADIR CONSTRAINT DE QUE UNJUGADOR NO SE PUEDE VOLVER A UNIR A LA MISMA PARTIDA
	  	Optional<Game> game= gameService.findById(gameId);
	  	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      User currentUser = (User) auth.getPrincipal();
	      Player player = new Player();
	      player.setEvasion(true);
	      player.setGame(game.get());
	      player.setGlory(0);
	      player.setGold(0);
	      player.setHero(HeroType.GUERRERO_FEMENINO);//para probar si funciona, después él tendría que escoger el que quiera
	      player.setWounds(0);
	      player.setUser(userService.findByUsername(currentUser.getUsername()));
	      playerService.savePlayer(player);
	  	return "redirect:/games/";
	  }



    
}


