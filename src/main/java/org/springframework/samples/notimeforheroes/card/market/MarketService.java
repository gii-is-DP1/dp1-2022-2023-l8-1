package org.springframework.samples.notimeforheroes.card.market;

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

	//Crea una lista de cartas de mercado para un juego
	@Transactional()
	public List<MarketCardInGame> addMarket(Game game){
		List<MarketCardInGame> market = findAll().stream().map(card -> MarketCardInGame.createInGame(game, card)).collect(Collectors.toList());
		for (MarketCardInGame card:market) {
			saveMarketCardInGame(card);
		}
		return market;
	}

	//Añade cartas determinadas y no repetidas a una pila de cartas de mercado
	public void addCardToMarket(MarketCardInGame card, int gameId){

		Game currentGame = gameService.findById(gameId).get();
		List<MarketCardInGame> currentMarketSale = currentGame.getSale();


		List<MarketCard> marketCards = findAll(); // Obtenemos las cartas del mercado
		for(MarketCardInGame mk: currentMarketSale){ // Eliminamos las cartas para no añadir cartas repetidas
			marketCards.remove(mk.getMarketCard());
		}
		try{

			// Añadimos la siguiente carta al mercado
			MarketCardInGame mk =  MarketCardInGame.createInGame(currentGame, marketCards.get(0));
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
