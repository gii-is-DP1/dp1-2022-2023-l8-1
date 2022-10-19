package org.springframework.samples.petclinic.user;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.samples.petclinic.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User{
	
	@Id
	String username;
	
	String password;
	
	boolean enabled;

	String email;

	LocalDate birthDate;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Player> players;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Stats> stats;

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;

	

	
}
