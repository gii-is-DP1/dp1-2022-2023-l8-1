package org.springframework.samples.notimeforheroes.game;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.player.HeroType;
import org.springframework.samples.notimeforheroes.player.Player;
import org.springframework.samples.notimeforheroes.user.User;

public interface GameRepository extends CrudRepository<Game, Integer>{

    List<Game>  findAll();


    @Query("SELECT p FROM players p WHERE p.game.id = ?1")
    List<Player> findPlayersInGame(int id);


}
