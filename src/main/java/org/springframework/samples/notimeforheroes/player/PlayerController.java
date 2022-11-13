package org.springframework.samples.notimeforheroes.player;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
// @RequestMapping("")
public class PlayerController {

    private static final String VIEWS_PLAYER_LISTS = "admins/playerLists";
    private static final String VIEWS_PLAYERS_CREATE_FORM = "admins/createPlayerForm";


    private final PlayerService service;

    @Autowired
    public PlayerController(PlayerService service){
        this.service = service;
    }

    @GetMapping("/admins/players")
    public ModelAndView showPlayerList(){
        ModelAndView mav = new ModelAndView(VIEWS_PLAYER_LISTS);
        mav.addObject("players", service.findPlayers());
        return mav;
    }

    @GetMapping(value = "/admins/createPlayerForm")
	public ModelAndView createUserView(){
		ModelAndView mav = new ModelAndView(VIEWS_PLAYERS_CREATE_FORM);
        Player player = new Player();
		mav.addObject("player", player);
		return mav;

	}

	@PostMapping(value = "/admins/createPlayerForm")
	public ModelAndView saveUser(@Valid Player player,BindingResult br ){
		ModelAndView mav = null;
		if(br.hasErrors()){
			mav = new ModelAndView(VIEWS_PLAYERS_CREATE_FORM);
			mav.addAllObjects(br.getModel());
		}else{
			service.savePlayer(player);
			mav = showPlayerList();
			mav.addObject("message", "User saved correctly");
		}

		return mav;
	}




    
}
