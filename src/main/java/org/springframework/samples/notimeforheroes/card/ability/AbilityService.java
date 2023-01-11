package org.springframework.samples.notimeforheroes.card.ability;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.card.enemy.EnemyInGame;
import org.springframework.samples.notimeforheroes.game.Game;
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
			for(AbilityCardInGame card:player.getAbilityPile()){
				List<AbilityCardInGame> registradas = card.getAbilityCard().getAbilityCardInGame();
				registradas.remove(card);
				card.getAbilityCard().setAbilityCardInGame(registradas);
				card.setAbilityCard(null);
			}
			abilityCardInGameRepository.deleteByPlayerPile(player);
			player.setAbilityPile(null);
			List<AbilityCardInGame> nuevo_mazo = new ArrayList<AbilityCardInGame>();
			List<AbilityCardInGame> ability = findAll().stream().filter(x -> x.getHero().equals(hero)).map(card -> createPileInPlayer(player, card)).collect(Collectors.toList());
			for (AbilityCardInGame card:ability) {
				System.out.println(" =Añadida la carta= "+ card.toString());
				saveAbilityCardInGame(card);
				nuevo_mazo.add(card);
			}
			player.setAbilityPile(nuevo_mazo);
			System.out.println(" =============Estado tras reasignacióno=============== " + player.getAbilityPile());
			return ability;
		}
	}

	@Transactional
	public void playTargetedEnemyAbilityCard(Player player, AbilityCardInGame card, Game game, List<EnemyInGame> targets){
		AbilityType carta = card.getAbilityCard().getAbilityType();
		int damage = card.getAbilityCard().getDamage();
		switch (carta) {
			case COMPANERO_LOBO: // Daño 2, Previenes 2 de Daño
			case DISPARO_CERTERO: // Daño 3, Pierdes 1 cartas, Finalizas tu ataque
			case DISPARO_RAPIDO: // Daño 1, Roba 1 si es "Disparo rápido" úsala, sino ponla al fondo del mazo de Habilidad
			case EN_LA_DIANA: // Daño 4, Gana 1 de Gloria, Pierdes 1 carta
			case LLUVIA_DE_FLECHAS: // Daño 2, Esta carta daña a 2 enemigos y al héroe con menos heridas, en empate tú eliges
			case ATAQUE_BRUTAL: // Daño 3, Pierdes 1 carta
			case CARGA_CON_ESCUDO: // Daño 2, Previenes 2 de Daño
			case DOBLE_ESPADAZO: // Daño 2, Pierdes 1 carta
			case ESPADAZO: // Daño 1, si el primer "Espadazo" que juegas Roba 1
			case TODO_O_NADA: // Daño 1, Roba 1 carta y súmale su daño a esta carta, Recupera la carta que robaste
			case DISPARO_GELIDO: // Daño 1, El enemigo afectado no causa daño este turno, Roba 1
			case FLECHA_CORROSIVA: // Daño 1, Las siguientes cartas que dañen a este enemigo le hacen 1 más de daño, Pierdes 1 carta
			case GOLPE_DE_BASTON: // Daño 1, Si no es el primer "Golpe de bastón" usado contra este enemigo en lugar de 1 esta carta hace 2 de daño
			case PROYECTIL_IGNEO: // Daño 2, Gana 1 de Gloria
			case TORRENTE_DE_LUZ: // Daño 2, Todos menos tú recuperan 2, Ganas 1 de Gloria
			case AL_CORAZON: // Daño 4, Si derrotas un enemigo con esto gana 1 Moneda si el primer "Al Corazón" del turno, Pierdes 1 carta
			case ATAQUE_FURTIVO: // Daño 2, Si derrotas un enemigo con esto gana 1 Moneda si el primer "Ataque Furtivo" del turno
			case BALLESTA_PRECISA: // Daño 2, Si ya usaste "Ballesta precisa" contra ese enemigo hace 1 punto más de daño
			case EN_LAS_SOMBRAS: // Daño 1, Previenes 2 de Daño
			case DAGA_ELFICA: //Daño 2, Coste 3, Si el héroe tiene como Proficiency "Pericia" se recupera tras jugarla, PROFICIENCIAS:  Distancia, Pericia, Melee
			case ALABARDA_ORCA: //Daño 4, Coste 5, PROFICIENCIAS: Melee
			case ARCO_COMPUESTO: //Daño 4, Coste 5, PROFICIENCIAS: Distancia
			///// Hacen target pero no daño
			case SUPERVIVENCIA: // Daño 0, Cambia 1 enemigo por el siguiente en el mazo de Horda
			case ESCUDO: // Previenes el daño de un enemigo, Finalizas tu ataque
			case ENGANAR: // Daño 0, Cuesta 2 monedas, El enemigo elegido no hace daño este turno
			case CAPA_ELFICA: // Daño 0, Coste 3, El enemigo seleccionado no hace daño este turno, PROFICIENCIAS : Distancia, Magia
			///// No requieren de obejtivo
			case RECOGER_FLECHAS: // Daño 0, Recupera un "Disparo Rápido", Baraja tu mazo de Habildades, Gana 1 moneda
			case PASO_ATRAS: // Daño 0, Roba 2
			case VOZ_DE_ALIENTO: // Todos Recuperan 2 cartas, Roba 1 carta y gana 1 de Gloria
			case AURA_PROTECTORA: //Daño 0, Cancela el daño del próximo ataque sufrido, Pierdes X cartas donde X es el número de enemigos en el campo
			case BOLA_DE_FUEGO: //Daño 2, Daña a todos los enemigos, El resto de héroes sufren 1 de Daño
			case ORBE_CURATIVO: // Daño 0, Todos Recuperan 2 cartas, Eliminas 1 herida de tu héroe, Elimina esta carta del juego
			case RECONSTITUCION: // Daño 0, Roba 1 carta, Recupera 2 cartas
			case ROBAR_BOLSILLOS: //Daño 0, Roba 1 moneda a cada héroe
			case SAQUEO: //Daño 0, Gana 1 moneda por cada Enemigo en el campo, Ganas 1 de Gloria
			case TRAMPA: // Daño 0, Al resolver el ataque de la horda derrotas al enemigo de mayor Fortaleza pero su botín se anula
			case POCION_CURATIVA: //Daño 0, Coste 8, Retira una herida de tu héroe, Eliminala del juego
			case PIEDRA_DE_AMOLAR: // Daño 0. Coste 4, Todas tus cartas hacen 1 más de daño este turno si hacían al menos 1 de Daño
			case VIAL_DE_CONJURACION: // Daño 0, Coste 5, Busca una carta de tu pila de Desgaste y ponla en tu mano
			case ELIXIR_DE_CONCENTRACION: // Daño 0, Coste 3, Roba 3 cartas
			case ARMADURA_DE_PLACAS: //Daño 0, Coste 4, Recuperas 4 cartas, PROFICIENCIAS: Melee
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
