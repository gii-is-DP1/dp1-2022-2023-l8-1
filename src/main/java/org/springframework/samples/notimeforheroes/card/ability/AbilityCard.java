package org.springframework.samples.notimeforheroes.card.ability;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.notimeforheroes.card.ConditionType;
import org.springframework.samples.notimeforheroes.player.HeroType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ability_cards")
public class AbilityCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    @NotNull
    private int damage;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="heroType")
    private HeroType hero;

    
    @Enumerated(EnumType.STRING)
    @Column(name="conditionType")
    private ConditionType condition;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "abilityType")
    private AbilityType abilityType;

    
    @OneToMany()
    private Set<AbilityCardInGame> abilityCardInGame;
    
}
