package org.springframework.samples.notimeforheroes.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Stats {
    @Id
    private long id;

    private int wins;

    // @ManyToOne(targetEntity = User.class)
    @ManyToOne(targetEntity = User.class)
    private User user;
    
}
