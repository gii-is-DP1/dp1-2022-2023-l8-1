package org.springframework.samples.notimeforheroes.player;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer>{
    //funciones básicas de repositorio
    List<Player> findAll();

    //Encuentra a los jugadores que estén en partida, (el estado es distinto a terminado)
    @Query("SELECT p FROM players p WHERE p.game.state <> 'TERMINADO'")
    List<Player> findPlayingPlayers();
}

