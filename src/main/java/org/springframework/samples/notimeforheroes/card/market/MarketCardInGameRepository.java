package org.springframework.samples.notimeforheroes.card.market;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketCardInGameRepository extends CrudRepository<MarketCardInGame, Integer>{
    //funciones básicas de un repositorio
}
