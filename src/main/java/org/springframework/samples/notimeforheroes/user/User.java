package org.springframework.samples.notimeforheroes.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
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

	@Column(name="username", unique = true)
	@NotEmpty
	private String username;
	
	@Column(name="password")
	@NotEmpty
	private String password;
	
	@Column(name="email", unique = true)
	@NotEmpty
	@Email
	private String email;
	
	@Column(name="birth_date")
	//@NotEmpty
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate birthDate;

	@Column(name="enabled")
	private boolean enabled;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Player> player;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Game> games;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Stats> stats;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Authorities> authorities;

	
}
