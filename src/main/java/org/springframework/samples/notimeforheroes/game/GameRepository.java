package org.springframework.samples.notimeforheroes.game;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer>{

    List<Game>  findAll();
    
}
