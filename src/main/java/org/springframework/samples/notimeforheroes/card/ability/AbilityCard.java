package org.springframework.samples.notimeforheroes.card.ability;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.springframework.samples.notimeforheroes.player.HeroType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AbilityCard {

    @Id

    private int damage;

    @Enumerated(EnumType.STRING)
    @Column(name="heroType")
    private HeroType hero;

    @Enumerated(EnumType.STRING)
    @Column(name = "abilityType")
    private AbilityType abilityType;

    

    
}
