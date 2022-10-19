package org.springframework.samples.petclinic.player;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "players")
public class Player {

    @Id
    private int id;

    private int glory;
    private int gold;
    private int wounds;

    private boolean evasion;


    @Enumerated(EnumType.STRING)
    @Column(name="heroType")
    private HeroType hero;

    @ManyToOne
    @JoinColumn(name = "users")
    private User user;




    
    

   
    
}
