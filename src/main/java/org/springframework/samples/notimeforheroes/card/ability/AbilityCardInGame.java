package org.springframework.samples.notimeforheroes.card.ability;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.samples.notimeforheroes.model.BaseEntity;
import org.springframework.samples.notimeforheroes.player.Player;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class AbilityCardInGame extends BaseEntity{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;

    private int damage;

    @ManyToOne(targetEntity = Player.class, cascade = CascadeType.ALL)
    private Player player;

    @ManyToOne(targetEntity = AbilityCard.class, cascade = CascadeType.ALL)
    private AbilityCard abilityCard;
    
}
