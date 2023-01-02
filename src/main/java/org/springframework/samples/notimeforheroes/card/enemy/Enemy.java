package org.springframework.samples.notimeforheroes.card.enemy;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.notimeforheroes.card.ConditionType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name= "enemies")
public class Enemy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    private int endurance;

    @NotNull
    private int glory;


    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EnemyType type;

    @Enumerated(EnumType.STRING)
    @Column(name="conditionType")
    private ConditionType condition;

    @NotNull
    @Column(name = "asset")
    private String asset;

    @OneToMany()
    private List<EnemyInGame> enemyInGame;


    
}
