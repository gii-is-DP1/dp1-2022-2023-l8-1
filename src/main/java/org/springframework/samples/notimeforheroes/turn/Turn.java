package org.springframework.samples.notimeforheroes.turn;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.player.Player;
import org.springframework.samples.notimeforheroes.card.ability.AbilityCardInGame;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Turn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="damageReduction")
	private int damageReduction;

    @OneToMany(mappedBy="turn")
	private List<AbilityCardInGame> cardsPlayed;

    @OneToOne(cascade = CascadeType.ALL)
    private Player player;

    @Enumerated(EnumType.STRING)
    private PhaseType type;

    @ManyToOne(targetEntity = Game.class)
    private Game game;


    public String toString(){
        return type.toString();
    }
    
}
