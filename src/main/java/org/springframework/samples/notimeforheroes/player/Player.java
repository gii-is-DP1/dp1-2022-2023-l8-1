package org.springframework.samples.notimeforheroes.player;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.samples.notimeforheroes.model.BaseEntity;
import org.springframework.samples.notimeforheroes.user.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "players")
public class Player extends BaseEntity{


    private int glory;
    private int gold;
    private int wounds;

    private boolean evasion;


    @Enumerated(EnumType.STRING)
    @Column(name="heroType")
    private HeroType hero;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    
    




    
    

   
    
}
