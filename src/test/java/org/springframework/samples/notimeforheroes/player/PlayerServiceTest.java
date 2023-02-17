package org.springframework.samples.notimeforheroes.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.notimeforheroes.card.ability.AbilityCardInGame;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PlayerServiceTest {
    @Autowired
    protected PlayerService playerService;
    @Autowired
    protected UserService userService;
    @Test
    public void createPlayerPositive() {
    	Integer n = playerService.findPlayers().size();
    	Player player = new Player();
    	User user = userService.findUser(2).get();
    	player.setEvasion(false);
    	player.setGlory(0);
    	player.setGold(0);
    	player.setWounds(0);
    	player.setUser(user);
    	playerService.savePlayer(player);
    	assertTrue(playerService.findPlayerById(n+1).isPresent());
    }
    @Test
    public void createPlayerNegative() {
    	Player player = new Player();
    	User user = userService.findUser(2).get();
    	player.setEvasion(false);
    	player.setGlory(-1);
    	player.setGold(-1);
    	player.setWounds(-1);
    	player.setUser(user);
    	assertThrows(ConstraintViolationException.class,() -> playerService.savePlayer(player), "Try to insert negative numbers");
    }
    
    @Test
    public void deletePlayerPositive() {
    	Player player = playerService.findPlayerById(2).get();
    	playerService.delete(player);
    	assertFalse(playerService.findPlayerById(2).isPresent());
    }
    
    @Test
    public void deletePlayerNegative() {
    	assertThrows(NoSuchElementException.class,()->playerService.delete(playerService.findPlayerById(100).get()));
    }

	@Test void pujarCartaPositive(){
		Player player = playerService.findPlayerById(2).get();
		List<AbilityCardInGame> hand = player.getAbilityHand();
		AbilityCardInGame card = hand.get(0);
		playerService.pujarCarta(player, card.getId());
		assertEquals(player.getCartasPuja().get(0), card);
	}
}