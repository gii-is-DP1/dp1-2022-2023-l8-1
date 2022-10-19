package org.springframework.samples.petclinic.game;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.player.Player;

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
    // @JoinColumn(name = "player", referencedColumnName = "player")
    Player winner;

    GameState state;

    

    // List<MarketCard> marketPile;
    // List<MarketCard> sale;
    



}
