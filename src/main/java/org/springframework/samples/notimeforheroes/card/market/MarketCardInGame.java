package org.springframework.samples.notimeforheroes.card.market;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class MarketCardInGame {

    @Id
    private int id;
    
}
