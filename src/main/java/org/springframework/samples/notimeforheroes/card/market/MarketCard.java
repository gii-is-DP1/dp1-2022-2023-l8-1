package org.springframework.samples.notimeforheroes.card.market;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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

    @NotNull
    @Min(1)
    private int price;

    private int damage;

    @Enumerated(EnumType.STRING)
    @Column(name="profiency1")
    private HeroType profiency1;

    @Enumerated(EnumType.STRING)
    @Column(name="profiency2")
    private HeroType profiency2;

    @Enumerated(EnumType.STRING)
    @Column(name="profiency3")
    private HeroType profiency3;

    @Enumerated(EnumType.STRING)
    @Column(name="profiency4")
    private HeroType profiency4;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private MarketCardType type;
     
}
