package org.springframework.samples.notimeforheroes.card.market;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.player.Player;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class MarketCardInGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = Player.class, cascade = CascadeType.ALL)
    private Player player;

    @ManyToOne(targetEntity = Game.class)
    private Game game;

//    @ManyToOne(targetEntity=MarketCard.class, cascade = CascadeType.ALL)
//    private MarketCard marketCard;
    
}
