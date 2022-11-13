package org.springframework.samples.notimeforheroes.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int wins;

    // @ManyToOne(targetEntity = User.class)
    @ManyToOne(targetEntity = User.class)
    private User user;
    
}
