package org.springframework.samples.notimeforheroes.card.market;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.player.Player;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class MarketCardInGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = Player.class, cascade = CascadeType.ALL)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(targetEntity = Game.class)
    @JoinColumn(name="game_id")
    private Game game;
    
    @ManyToOne(targetEntity = Game.class)
    @JoinColumn(name="game_sale_id")
    private Game gameOnSale;

    @ManyToOne(targetEntity=MarketCard.class, cascade = CascadeType.ALL)
    @JoinColumn(name="market_card_id")
    private MarketCard marketCard;
    

    //Asocia cartas de mercado a un juego
    public static MarketCardInGame createInGame(Game game, MarketCard card) {
    	MarketCardInGame cardIG = new MarketCardInGame();
    	cardIG.setGame(game);
    	cardIG.setMarketCard(card);
    	return cardIG;
    }

    public String toString(){
        return this.getMarketCard().getType().toString();
    }
}
