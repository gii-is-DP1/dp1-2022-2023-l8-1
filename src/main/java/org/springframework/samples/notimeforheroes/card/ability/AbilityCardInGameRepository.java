package org.springframework.samples.notimeforheroes.card.ability;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.player.Player;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityCardInGameRepository extends CrudRepository<AbilityCardInGame, Integer>{
    //Funciones básicas de repositorio para abilityCardInGame

    void deleteByPlayer(Player player);

    void deleteByPlayerPile(Player player);

    Iterable<AbilityCardInGame> findAllByPlayer(Player Player);

    Iterable<AbilityCardInGame> findAllByPlayerPile(Player Player);
}
