package org.springframework.samples.notimeforheroes.card.ability;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityCardRepository extends CrudRepository<AbilityCard, Integer>{
    //Funciones básicas de repositorio para abilityCard
}
