package org.springframework.samples.notimeforheroes.friends;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.notimeforheroes.model.BaseEntity;
import org.springframework.samples.notimeforheroes.user.User;

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
	private String userName;

    @Column(name="friendUsername")
	@NotNull
    private String friendUsername;	
	
}
