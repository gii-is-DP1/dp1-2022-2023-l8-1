package org.springframework.samples.notimeforheroes.card.ability;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.player.HeroType;
import org.springframework.samples.notimeforheroes.player.Player;
import org.springframework.samples.notimeforheroes.player.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class AbilityService {
	//Repositorios de Cartas de Habilidad y Servicio de Jugador como variables
    @Autowired
    private AbilityCardRepository abilityCardRepository;

    @Autowired
    private AbilityCardInGameRepository abilityCardInGameRepository;

    @Autowired
    private PlayerService playerService;

	//Encuentra todas las AbilityCards y las asocia a una lista
    public List<AbilityCard> findAll() {
        return (List<AbilityCard>) abilityCardRepository.findAll();
    }


	//Filtrar las cartas por HeroType y las asocia a una lista y player
    @Transactional()
	public List<AbilityCardInGame> addAbilityCard(Player player, HeroType hero){
		List<AbilityCardInGame> ability = findAll().stream().filter(x -> x.getHero().equals(hero)).map(card -> AbilityCardInGame.createInPlayer(player, card)).collect(Collectors.toList());
		for (AbilityCardInGame card:ability) {
			saveAbilityCardInGame(card);
		}
		return ability;
	}

	//Función save simple
    public void saveAbilityCardInGame(AbilityCardInGame card) {
		abilityCardInGameRepository.save(card);
	}

	//Encontrar una AbilityCardInGame por Id
	public AbilityCardInGame findById(int abilityCardId){
		return abilityCardInGameRepository.findById(abilityCardId).get();
	}


	//Para añadir cartas no repetidas a una pila
    public void addCardToPile(AbilityCardInGame card, int playerId){

		Player player = playerService.findPlayerById(playerId).get();
		List<AbilityCardInGame> currentAbilityPile = player.getAbilityPile();


		List<AbilityCard> abilityCards = findAll(); // Cartas de todo el juego
		for(AbilityCardInGame ab: currentAbilityPile){ // Eliminamos las cartas para no añadir cartas repetidas
			abilityCards.remove(ab.getAbilityCard());
		}
		try{

			// Añadimos la siguiente carta a la pila
			AbilityCardInGame ab =  AbilityCardInGame.createInPlayer(player, abilityCards.get(0));
			ab.setPlayerPile(player);
			
			saveAbilityCardInGame(ab);
		}catch(Exception e){ // Cuando no hay más elementos
			return;
		}
		

	}
    
}
