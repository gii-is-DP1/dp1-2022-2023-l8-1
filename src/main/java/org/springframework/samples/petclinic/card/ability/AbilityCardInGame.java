package org.springframework.samples.petclinic.card.ability;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
