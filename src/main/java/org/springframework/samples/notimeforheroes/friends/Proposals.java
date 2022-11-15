package org.springframework.samples.notimeforheroes.friends;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "proposals")
public class Proposals extends BaseEntity{

	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;

    @Column(name="senderUsername")
    @NotNull
	private String senderUsername;

    @Column(name="receiverUsername")
    @NotNull
    private String receiverUsername;

    @Enumerated(EnumType.STRING)
    @Column(name="proposalType")
    @NotNull
    private ProposalType proposalType;

    @Column(name="gameId")
    private Integer gameId;
	
}
