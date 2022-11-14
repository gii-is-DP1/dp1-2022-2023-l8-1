package org.springframework.samples.notimeforheroes.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "proposals")
public class Proposals extends BaseEntity{

	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;

    @Column(name="sender_username")
    @NotNull
	private String sender_username;

    @Column(name="receiver_username")
    @NotNull
    private String receiver_username;

    @Enumerated(EnumType.STRING)
    @Column(name="proposal_type")
    @NotNull
    private ProposalType proposal_type;

    @Column(name="game_id")
    private Integer game_id;
	
}
