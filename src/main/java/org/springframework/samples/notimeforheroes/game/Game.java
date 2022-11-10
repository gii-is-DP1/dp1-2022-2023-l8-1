package org.springframework.samples.notimeforheroes.game;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Generated;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private Date startTime;
    private Date endTime;

    @Size(min = 0)
    private int minPlayers;

    @Size(max = 4)
    private int maxPlayers;

    private boolean hasScenes;

    @OneToOne(cascade = CascadeType.ALL)
    private Player winner;


    @Enumerated(EnumType.STRING)
    @Column(name ="state")
    private GameState state;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;


    @Size(min = 0, max = 4)
    @OneToMany(cascade = CascadeType.ALL)
    private List<Player> players;





    



}
