package org.springframework.samples.petclinic.user;

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
    int wins;

    // @ManyToOne(targetEntity = User.class)
    @ManyToOne
    @JoinColumn(name = "users")
    private User user;
    
}
