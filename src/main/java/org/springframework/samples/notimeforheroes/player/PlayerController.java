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
	//Variables para los jsp usados
    private static final String VIEWS_PLAYER_LISTS = "admins/playerLists";
    private static final String VIEWS_PLAYERS_CREATE_FORM = "admins/createPlayerForm";
    private static final String VIEWS_PLAYER_CARDS_LIST = "players/cardsInHandList";
	private static final String VIEW_HERO_CARD = "players/heroCard";
	private static final String VIEW_ABILITY_PILE = "players/abilityPile";
	private static final String VIEW_MARKET_HAND = "players/marketHand";

	//Servicios como variables y posterior asociación a este controlador
    private final PlayerService playerService;
    
    private final GameService gameService;
    
    private final UserService userService;

    @Autowired
    public PlayerController(PlayerService playerService, GameService gameService,UserService userService){
        this.playerService = playerService;
		this.gameService = gameService;
		this.userService = userService;
    }
	
	//controlador vista para las cartas asociadas a un jugador
        @GetMapping(value="/players/{playerId}")
        public ModelAndView showCardList(@PathVariable("playerId")int playerId) {
        ModelAndView model = new ModelAndView(VIEWS_PLAYER_CARDS_LIST);
        Player player = this.playerService.findPlayerById(playerId).get();
		model.addObject("player", player);
		return model;
        
    }

	//controlador vista para la carta de heroe asociada a un jugador
	@GetMapping(value="/players/heroCard/{playerId}")
	public ModelAndView showHeroCard(@PathVariable("playerId")int playerId) {
		ModelAndView model = new ModelAndView(VIEW_HERO_CARD);
		Player player = this.playerService.findPlayerById(playerId).get();
		model.addObject("player", player);
		return model;
	}

	//controlador vista para la pila de cartas de abilidad asociada a un jugador
	@GetMapping(value="/players/abilityPile/{playerId}")
	public ModelAndView showAbilityPile(@PathVariable("playerId")int playerId) {
		ModelAndView model = new ModelAndView(VIEW_ABILITY_PILE);
		Player player = this.playerService.findPlayerById(playerId).get();
		model.addObject("player", player);
		return model;
	}
	
	//controlador vista para la mano de cartas de mercado asociada a un jugador
	@GetMapping(value="/players/marketHand/{playerId}")
	public ModelAndView showMarketHand(@PathVariable("playerId")int playerId) {
		ModelAndView model = new ModelAndView(VIEW_MARKET_HAND);
		Player player = this.playerService.findPlayerById(playerId).get();
		model.addObject("player", player);
		return model;
	}

	//controlador vista de toda la lista de jugadores
    @GetMapping("/admins/players")
    public ModelAndView showPlayerList(){
        ModelAndView mav = new ModelAndView(VIEWS_PLAYER_LISTS);
        mav.addObject("players", playerService.findPlayers());
        return mav;
    }

	//ModelAtrribute para la población de HeroTypes
    @ModelAttribute("types")
	public Collection<HeroType> populateHeroType() {
		return List.of(HeroType.values());
	}

	//controlador vista para la creación de un player
    @GetMapping(value = "/admins/createPlayerForm")
	public ModelAndView createPlayerView(){
		ModelAndView mav = new ModelAndView(VIEWS_PLAYERS_CREATE_FORM);

        Player player = new Player();

		mav.addObject("player", player);
		return mav;

	}

	//Controlador post para la creación de un jugador
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

	//Controlador para la unión de un usuario a un juego
	@GetMapping("/games/{gameId}/join")
	public String joinGame(@PathVariable("gameId")int gameId) {
		Optional<Game> game= gameService.findById(gameId);
	  	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User currentUser = (User) auth.getPrincipal();
	    List<Player> playersInGame = playerService.findPlayersInGame();
	    //if(!players.stream().anyMatch(x->x.getUser()==userService.findByUsername(currentUser.getUsername()))) {//si el user ya está en la partida no se podrá unir
		//	Player newPlayer = new Player();
		//	org.springframework.samples.notimeforheroes.user.User user = userService.findByUsername(currentUser.getUsername());
		//	playerService.createPlayer(newPlayer, game.get(), user);
		//}
		if(playersInGame.stream().anyMatch(x->x.getUser()==userService.findByUsername(currentUser.getUsername()))) {
			return "redirect:/games/";
		}
		Player newPlayer = new Player();
		org.springframework.samples.notimeforheroes.user.User user = userService.findByUsername(currentUser.getUsername());
		playerService.createPlayer(newPlayer, game.get(), user);
		
	    return "redirect:/games/"+gameId+"/lobby";
	}

	//Controlador para la eliminación de un player determinado
	@GetMapping(value="/admins/players/delete/{playerId}")
	public String deleteUser(@PathVariable("playerId") int playerId, ModelMap model) {
		Optional<Player> player = playerService.findPlayerById(playerId);
		if(player.isPresent()) {
			model.addAttribute("message", "player successfully deleted");//no se muestra, no sé por qué
			playerService.delete(player.get()); //También estaría bien añadir que aparezca botón de confirmación
		}else {
			model.addAttribute("message", "player not found");
		}
		return "redirect:/admins/players";
	}


    
}


