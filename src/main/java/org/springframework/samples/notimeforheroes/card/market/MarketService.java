package org.springframework.samples.notimeforheroes.card.market;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MarketService {

	//Repositorios de mercado y servicio de Game como variables
	@Autowired
	private MarketCardRepository marketRepository;
	@Autowired
	private MarketCardInGameRepository marketCardInGameRepository;
	@Autowired
	private GameService gameService;

	//Encuentra todas las MarketCard
	public List<MarketCard> findAll(){
		return (List<MarketCard>) marketRepository.findAll();
	}

	//Crea una lista de cartas de mercado para un juego, añadiendo a pila y a la venta
	@Transactional()
	public List<MarketCardInGame> addMarket(Game game){
		List<MarketCard> market = findAll();
		Collections.shuffle(market);
		List<MarketCardInGame> marketIG = market.stream().map(card -> MarketCardInGame.createInGame(game, card)).collect(Collectors.toList());
		for(int i =0;i<5;i++) {//para tener 5 cartas on sale al empezar la partida
			MarketCardInGame card = marketIG.get(i);
			card.setGame(null);
			card.setGameOnSale(game);
		}
		for (MarketCardInGame card:marketIG) {
			saveMarketCardInGame(card);
		}
		return marketIG;
	}

	//Añade cartas determinadas al sale y no repetidas a una pila de cartas de mercado
	@Transactional()
	public void addCardToMarket(MarketCardInGame card, int gameId){

		Game currentGame = gameService.findById(gameId).get();
		List<MarketCardInGame> currentMarketPile = currentGame.getMarketPile();//obtenemos las cartas de la pila

		try{
			
			MarketCardInGame mk = currentMarketPile.get(0);
			mk.setGame(null);
			mk.setGameOnSale(currentGame);
			
			saveMarketCardInGame(mk);
		}catch(Exception e){ // Cuando no hay más elementos en la lista de cartas de mercado
			return;
		}
		

	}

	//función save básica
	public void saveMarketCardInGame(MarketCardInGame card) {
		marketCardInGameRepository.save(card);
	}

	//Encuentra una carta de mercado por su id
	public MarketCardInGame findById(int marketCardId){
		return marketCardInGameRepository.findById(marketCardId).get();
	}
}
