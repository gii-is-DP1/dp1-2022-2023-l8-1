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
import org.springframework.ui.ModelMap;
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
	private static final String VIEW_HERO_CARD = "players/heroCard";
	private static final String VIEW_ABILITY_PILE = "players/abilityPile";
	private static final String VIEW_MARKET_HAND = "players/marketHand";

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

	@GetMapping(value="/players/heroCard/{playerId}")
	public ModelAndView showHeroCard(@PathVariable("playerId")int playerId) {
		ModelAndView model = new ModelAndView(VIEW_HERO_CARD);
		Player player = this.playerService.findPlayerById(playerId).get();
		model.addObject("player", player);
		return model;
	}

	@GetMapping(value="/players/abilityPile/{playerId}")
	public ModelAndView showAbilityPile(@PathVariable("playerId")int playerId) {
		ModelAndView model = new ModelAndView(VIEW_ABILITY_PILE);
		Player player = this.playerService.findPlayerById(playerId).get();
		model.addObject("player", player);
		return model;
	}
	
	@GetMapping(value="/players/marketHand/{playerId}")
	public ModelAndView showMarketHand(@PathVariable("playerId")int playerId) {
		ModelAndView model = new ModelAndView(VIEW_MARKET_HAND);
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
	public String joinGame(@PathVariable("gameId")int gameId) {
		Optional<Game> game= gameService.findById(gameId);
	  	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User currentUser = (User) auth.getPrincipal();
	    List<Player> players = game.get().getPlayer();
	    if(!players.stream().anyMatch(x->x.getUser()==userService.findByUsername(currentUser.getUsername()))) {//si el user ya est?? en la partida no se podr?? unir
			Player newPlayer = new Player();
			org.springframework.samples.notimeforheroes.user.User user = userService.findByUsername(currentUser.getUsername());
			playerService.createPlayer(newPlayer, game.get(), user);
		}
	    return "redirect:/games/"+gameId+"/lobby";
	}
	@GetMapping(value="/admins/players/delete/{playerId}")
	public String deleteUser(@PathVariable("playerId") int playerId, ModelMap model) {
		Optional<Player> player = playerService.findPlayerById(playerId);
		if(player.isPresent()) {
			model.addAttribute("message", "player successfully deleted");//no se muestra, no s?? por qu??
			playerService.delete(player.get()); //Tambi??n estar??a bien a??adir que aparezca bot??n de confirmaci??n
		}else {
			model.addAttribute("message", "player not found");
		}
		return "redirect:/admins/players";
	}


    
}


