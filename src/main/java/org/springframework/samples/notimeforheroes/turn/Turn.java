package org.springframework.samples.notimeforheroes.turn;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Turn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    private Player player;

    @Enumerated(EnumType.STRING)
    private PhaseType type;

    @ManyToOne(targetEntity = Game.class)
    private Game game;
    
}
