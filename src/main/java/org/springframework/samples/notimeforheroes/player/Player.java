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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.samples.notimeforheroes.card.ability.AbilityCardInGame;
import org.springframework.samples.notimeforheroes.card.market.MarketCardInGame;
import org.springframework.samples.notimeforheroes.game.Game;
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

    @NotNull
    @Min(0)
    private int glory;

    @NotNull
    @Min(0)
    private int gold;

    @NotNull
    @Min(0)
    private int wounds;

    @NotNull
    private boolean evasion;

    @Enumerated(EnumType.STRING)
    @Column(name="profiency")
    private Profiency profiency;

    @Enumerated(EnumType.STRING)
    @Column(name="heroType")
    private HeroType hero;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne()
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany()
    private List<MarketCardInGame> marketHand;

    @OneToMany()
    private List<AbilityCardInGame> abilityHand;

    @OneToMany()
    private List<AbilityCardInGame> discardPile;

    @OneToMany()
    private List<AbilityCardInGame> abilityPile;

}
