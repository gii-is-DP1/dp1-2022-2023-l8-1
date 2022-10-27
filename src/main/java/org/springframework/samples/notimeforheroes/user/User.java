package org.springframework.samples.notimeforheroes.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.model.BaseEntity;
import org.springframework.samples.notimeforheroes.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity{
	

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	@NotBlank
	private String email;

	@NotBlank
	private LocalDate birthDate;

	private boolean enabled;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Player> player;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Game> games;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Stats> stats;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;

	// private Boolean setEnabled(){

	// }

	
}
