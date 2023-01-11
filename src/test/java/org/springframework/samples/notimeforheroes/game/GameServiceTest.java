package org.springframework.samples.notimeforheroes.game;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class)) 
public class GameServiceTest {
    @Autowired
    protected GameService service;
    @Autowired
    protected UserService userService;
    @Test
    public void createGamePositive() {
        User admin = userService.findUser(1).get();
        Game game = new Game();
        game.setMinPlayers(2);
        game.setMaxPlayers(3);
        game.setState(GameState.LOBBY);
        game.setStartTime(new Date());
        game.setHasScenes(false);
        game.setUsername("admin");
        game.setUser(admin);
        service.saveGame(game);
        assertTrue(service.findById(game.getId()).isPresent());
    }

    @Test
    public void createGameNegative() {
        User admin = userService.findUser(1).get();
        Game game = Game.builder().minPlayers(-1).maxPlayers(5).state(GameState.LOBBY).startTime(new Date())
                        .hasScenes(false).username("admin").user(admin).build();
        assertThrows(ConstraintViolationException.class, () -> service.saveGame(game));

    }
    
}
