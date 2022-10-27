package org.springframework.samples.notimeforheroes.game;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.samples.notimeforheroes.player.Player;
import org.springframework.samples.notimeforheroes.user.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "games")
public class Game {

    @Id
    
    Date startTime;
    Date endTime;

    int minPlayers;
    int maxPLayers;

    boolean hasScenes;

    @OneToOne(cascade = CascadeType.ALL)
    Player winner;

    GameState state;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;



    // List<MarketCard> marketPile;
    // List<MarketCard> sale;
    



}
