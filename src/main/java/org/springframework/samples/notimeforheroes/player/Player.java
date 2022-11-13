package org.springframework.samples.notimeforheroes.player;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.samples.notimeforheroes.card.ability.AbilityCard;
import org.springframework.samples.notimeforheroes.card.ability.AbilityCardInGame;
import org.springframework.samples.notimeforheroes.card.market.MarketCardInGame;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.model.BaseEntity;
import org.springframework.samples.notimeforheroes.user.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "players")
public class Player{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;


    private int glory;
    private int gold;
    private int wounds;

    private boolean evasion;


    @Enumerated(EnumType.STRING)
    @Column(name="heroType")
    private HeroType hero;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    
    // @ManyToOne(targetEntity = Game.class)
    // // @JoinColumn(name = "game_id")
    // private Game game;

    @OneToMany()
    private List<MarketCardInGame> marketHand;

    @OneToMany()
    private List<AbilityCardInGame> abilityHand;

    @OneToMany()
    private List<AbilityCardInGame> abilityPile;

    @OneToMany()
    private List<AbilityCardInGame> discardPile;
    
}
