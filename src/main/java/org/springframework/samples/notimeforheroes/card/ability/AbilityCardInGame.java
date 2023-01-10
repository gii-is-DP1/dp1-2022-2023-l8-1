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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = Player.class, cascade = CascadeType.ALL)
    @JoinColumn(name="player_id")
    private Player player;
    
    @ManyToOne(targetEntity = Player.class, cascade = CascadeType.ALL)
    @JoinColumn(name="player_discard_id")
    private Player playerDiscard;
    
    @ManyToOne(targetEntity = Player.class, cascade = CascadeType.ALL)
    @JoinColumn(name="player_pile_id")
    private Player playerPile;

    @ManyToOne(targetEntity = AbilityCard.class, cascade = CascadeType.ALL)
    @JoinColumn(name="ability_card_id")
    private AbilityCard abilityCard;

    public static AbilityCardInGame createInPlayer(Player player, AbilityCard card) { //Asociar AbilityCards a un Player
    	AbilityCardInGame cardIP = new AbilityCardInGame();
    	cardIP.setPlayer(player);
    	cardIP.setAbilityCard(card);
    	return cardIP;
    }

    public String toString(){
        return abilityCard.getAbilityType().toString();
    }
    
}
