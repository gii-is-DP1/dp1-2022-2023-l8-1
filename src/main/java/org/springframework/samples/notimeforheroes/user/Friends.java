package org.springframework.samples.notimeforheroes.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.notimeforheroes.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "friends")
public class Friends extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;

	@Column(name="username")
	@NotNull
	private String username;

    @Column(name="friend_username")
	@NotNull
    private String friend_username;	
	
}
