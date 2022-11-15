package org.springframework.samples.notimeforheroes.player;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.card.ability.AbilityCardInGame;

public interface PlayerRepository extends CrudRepository<Player, Integer>{

    List<Player> findAll();


    
}

