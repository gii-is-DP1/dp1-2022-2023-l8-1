package org.springframework.samples.notimeforheroes.player;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer>{
    //funciones básicas de repositorio
    List<Player> findAll();
}

