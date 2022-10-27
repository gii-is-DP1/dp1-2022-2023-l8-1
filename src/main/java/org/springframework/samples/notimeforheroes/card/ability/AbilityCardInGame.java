package org.springframework.samples.notimeforheroes.card.ability;

import javax.persistence.Entity;
import javax.persistence.Id;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class AbilityCardInGame {

    @Id

    private int damage;

    // @ManyToOne
    // @JoinColumn(name = "player_id")
    // private AbilityCard abilityCard;
    
}
