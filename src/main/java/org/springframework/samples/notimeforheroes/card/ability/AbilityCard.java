package org.springframework.samples.notimeforheroes.card.ability;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.samples.notimeforheroes.model.BaseEntity;
import org.springframework.samples.notimeforheroes.player.HeroType;
import org.springframework.samples.notimeforheroes.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ability_cards")
public class AbilityCard extends BaseEntity{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY )
//    private int id;

    private int damage;

    @Enumerated(EnumType.STRING)
    @Column(name="heroType")
    private HeroType hero;

    @Enumerated(EnumType.STRING)
    @Column(name = "abilityType")
    private AbilityType abilityType;

    @OneToMany()
    private Set<AbilityCardInGame> abilityCardInGame;

    

    
}
