package org.springframework.samples.notimeforheroes.card.ability;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.samples.notimeforheroes.player.Player;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class AbilityCardInGame{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = Player.class, cascade = CascadeType.ALL)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(targetEntity = AbilityCard.class, cascade = CascadeType.ALL)
    @JoinColumn(name="ability_card_id")
    private AbilityCard abilityCard;

    public String toString(){
        return abilityCard.getAbilityType().toString();
    }
    
}
