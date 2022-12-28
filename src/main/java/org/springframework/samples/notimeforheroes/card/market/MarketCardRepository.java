package org.springframework.samples.notimeforheroes.card.market;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketCardRepository extends CrudRepository<MarketCard, Integer>{

}
