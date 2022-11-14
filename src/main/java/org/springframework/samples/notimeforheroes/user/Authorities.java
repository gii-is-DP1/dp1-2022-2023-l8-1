package org.springframework.samples.notimeforheroes.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.samples.notimeforheroes.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authorities")
public class Authorities extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;
	
	@Column(name="username")
	private String username;
	
	@Size(min = 3, max = 50)
	String authority;

	
	
}