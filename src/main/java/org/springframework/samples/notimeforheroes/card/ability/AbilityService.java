package org.springframework.samples.notimeforheroes.card.ability;

import java.util.ArrayList;
import java.util.Collections;
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


	//Asigna el mazo correspondiente en el lobby al jugador correspondiente
    @Transactional
	public List<AbilityCardInGame> addAbilityCards(Player player, HeroType hero){
		System.out.println(player.getAbilityPile());
		List<AbilityCardInGame> mazo_actual = new ArrayList<AbilityCardInGame>();
    	abilityCardInGameRepository.findAllByPlayerPile(player).forEach(mazo_actual::add);
		if (mazo_actual.isEmpty()){
			System.out.println(" =============Sin Mazo=============== ");
			List<AbilityCardInGame> ability = findAll().stream().filter(x -> x.getHero().equals(hero)).map(card -> createPileInPlayer(player, card)).collect(Collectors.toList());
			for (AbilityCardInGame card:ability) {
				System.out.println(" = Añadida la carta= "+ card.toString());
				saveAbilityCardInGame(card);
				System.out.println("Carta del juagador "+card.getPlayerPile()+ " habilidad = " + card.getAbilityCard());
			}
			return ability;
		}else{
			System.out.println(" =============Con Mazo=============== ");
			abilityCardInGameRepository.deleteByPlayerPile(player);
			player.setAbilityPile(null);
			List<AbilityCardInGame> ability = findAll().stream().filter(x -> x.getHero().equals(hero)).map(card -> createPileInPlayer(player, card)).collect(Collectors.toList());
			for (AbilityCardInGame card:ability) {
				System.out.println(" =Añadida la carta= "+ card.toString());
				saveAbilityCardInGame(card);
			}
			System.out.println(" =============Estado tras reasignacióno=============== " + abilityCardInGameRepository.findAllByPlayerPile(player));
			return ability;
		}
	}

	@Transactional
	public void addStartingHand(Player player) {
		List<AbilityCardInGame> mazo_actual = new ArrayList<AbilityCardInGame>();
		List<AbilityCardInGame> mano_actual = new ArrayList<AbilityCardInGame>();
    	abilityCardInGameRepository.findAllByPlayerPile(player).forEach(mazo_actual::add);
		System.out.println(player.getAbilityPile());
		Collections.shuffle(mazo_actual);
		for(int i = 0; i < 4; i++) {
			AbilityCardInGame carta = mazo_actual.get(i);
			carta.setPlayer(player);
			carta.setPlayerPile(null);
			mazo_actual.remove(carta);
			mano_actual.add(carta);

		}
		player.setAbilityPile(mazo_actual);
		player.setAbilityHand(mano_actual);
		System.out.println("El mazo que ha de haber = " + mazo_actual);
		System.out.println("El mazo de database = " + player.getAbilityPile());
		System.out.println("La mano de database = " + player.getAbilityHand());
	}

	public static AbilityCardInGame createPileInPlayer(Player player, AbilityCard card) { //Asociar AbilityCards a un Player
    	AbilityCardInGame cardIP = new AbilityCardInGame();
    	cardIP.setPlayerPile(player);
    	cardIP.setAbilityCard(card);
    	return cardIP;
    }

	public AbilityCardInGame addCardtoHandFromPile(Player player, AbilityCard card) { //Asociar AbilityCards a un Player
    	AbilityCardInGame cardIH = new AbilityCardInGame();
    	cardIH.setPlayer(player);
    	cardIH.setAbilityCard(card);
		System.out.println(" Se ha creado la carta " + cardIH.getId() + " del player " + cardIH.getPlayer() + " con la habilidad " + cardIH.getAbilityCard());
    	return cardIH;
    }

	//Función save simple
    public void saveAbilityCardInGame(AbilityCardInGame card) {
		abilityCardInGameRepository.save(card);
	}

	//Encontrar una AbilityCardInGame por Id
	public AbilityCardInGame findById(int abilityCardId){
		return abilityCardInGameRepository.findById(abilityCardId).get();
	}

	//Función delete simple
    public void deleteAbilityCardInGame(AbilityCardInGame card) {
		abilityCardInGameRepository.delete(card);
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
