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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.samples.notimeforheroes.card.ConditionType;
import org.springframework.samples.notimeforheroes.player.Profiency;

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

    @NotNull
    private int damage;

    @Enumerated(EnumType.STRING)
    @Column(name="profiency1")
    private Profiency profiency1;

    @Enumerated(EnumType.STRING)
    @Column(name="profiency2")
    private Profiency profiency2;

    @Enumerated(EnumType.STRING)
    @Column(name="profiency3")
    private Profiency profiency3;

    @Enumerated(EnumType.STRING)
    @Column(name="profiency4")
    private Profiency profiency4;

    @Enumerated(EnumType.STRING)
    @Column(name="conditionType")
    private ConditionType condition;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private MarketCardType type;

    @NotNull
    @Column(name = "asset")
    private String asset;

    @OneToMany(mappedBy="marketCard")
    private Set<MarketCardInGame> marketCardInGame;


    public String toString(){
        return this.getType().toString();
    }
}
