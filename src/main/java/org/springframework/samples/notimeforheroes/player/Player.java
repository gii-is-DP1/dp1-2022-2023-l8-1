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
import javax.validation.constraints.Size;

import org.springframework.samples.notimeforheroes.card.ability.AbilityCardInGame;
import org.springframework.samples.notimeforheroes.card.market.MarketCardInGame;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "players")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy="player")
    private List<MarketCardInGame> marketHand;

    @OneToMany(mappedBy="player")
    private List<AbilityCardInGame> abilityHand;

    @OneToMany(mappedBy="playerDiscard")
    private List<AbilityCardInGame> discardPile;

    @OneToMany(mappedBy="playerMarketDiscard")
    private List<MarketCardInGame> marketDiscardPile;

    @OneToMany(mappedBy="playerPile")
    private List<AbilityCardInGame> abilityPile;


    // TODO: Hay que añadirlo al UML
    @Size(max = 2, min = 0)
    @OneToMany()
    private List<AbilityCardInGame> cartasPuja;


    public String toString(){
        return user.getUsername();
    }

}
