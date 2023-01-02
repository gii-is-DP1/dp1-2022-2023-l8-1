package org.springframework.samples.notimeforheroes.card.market;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MarketService {
	@Autowired
	private MarketCardRepository marketRepository;
	@Autowired
	private MarketCardInGameRepository marketCardInGameRepository;
	
	public List<MarketCard> findAll(){
		return (List<MarketCard>) marketRepository.findAll();
	}
	@Transactional()
	public List<MarketCardInGame> addMarket(Game game){
		List<MarketCardInGame> market = findAll().stream().map(card -> MarketCardInGame.createInGame(game, card)).collect(Collectors.toList());
		for (MarketCardInGame card:market) {
			saveMarketCardInGame(card);
		}
		return market;
	}
	
	public void saveMarketCardInGame(MarketCardInGame card) {
		marketCardInGameRepository.save(card);
	}
}
