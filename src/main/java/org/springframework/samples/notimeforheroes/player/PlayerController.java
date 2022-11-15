package org.springframework.samples.notimeforheroes.player;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.user.AuthoritiesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlayerController {

    private static final String VIEWS_PLAYER_CARDS_LIST = "players/cardsInHandList";

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService, AuthoritiesService authoritiesService) {
        this.playerService = playerService;
    }

    @GetMapping(value="/players/{playerId}")
    public ModelAndView showCardList(@PathVariable("playerId")int playerId) {
        ModelAndView model = new ModelAndView(VIEWS_PLAYER_CARDS_LIST);
		model.addObject("player",this.playerService.findPlayerById(playerId).get());
		return model;
        
    }
    
    
}
