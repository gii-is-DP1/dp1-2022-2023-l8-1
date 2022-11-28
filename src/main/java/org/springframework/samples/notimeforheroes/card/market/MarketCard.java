package org.springframework.samples.notimeforheroes.card.market;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.samples.notimeforheroes.player.HeroType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class MarketCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int price;
    private int damage;


    @Enumerated(EnumType.STRING)
    @Column(name="profiency")
    private HeroType profiency;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private MarketCardType type;

//    @OneToMany()
//    private Set<MarketCardInGame> marketCardInGame;

    
}
