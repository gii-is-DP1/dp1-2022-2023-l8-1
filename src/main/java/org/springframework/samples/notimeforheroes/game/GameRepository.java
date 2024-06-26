package org.springframework.samples.notimeforheroes.game;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.player.Player;

public interface GameRepository extends CrudRepository<Game, Integer>{
    //funciones básicas de repositorio
    List<Game>  findAll();

    //Encuentra jugadores asociados a un Game por su id
    @Query("SELECT p FROM players p WHERE p.game.id = ?1")
    List<Player> findPlayersInGame(int id);

//    @Query("")
//    void update(Game game);


}
