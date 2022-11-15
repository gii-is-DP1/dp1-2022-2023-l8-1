package org.springframework.samples.notimeforheroes.card.market;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.samples.notimeforheroes.player.Player;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class MarketCard {

    @Id

    private int price;
    private int profiency;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private MarketCardType type;

    
}
