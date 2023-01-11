package org.springframework.samples.notimeforheroes.player;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.card.ability.AbilityCardInGame;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    
    //Repositorio de Player como variable y posterior asociación a este servicio
    private PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    //Encuentra todos los Players
    public Collection<Player> findPlayers(){
        return playerRepository.findAll();
    }


    //Funcion save simple
    @Transactional
    public void savePlayer(Player player){
    	playerRepository.save(player);
    }

    //Creación de un player para un juego con valores determinados iniciales
    public void createPlayer(Player player, Game game, User user){
    	player.setEvasion(true);
	      player.setGame(game);
	      player.setGlory(0);
	      player.setGold(0);
	      //player.setHero(HeroType.GUERRERO_FEMENINO);//para probar si funciona, después él tendría que escoger el que quiera
	      player.setWounds(0);
	      player.setUser(user);
    	playerRepository.save(player);


    }

    
    //Encunetra Jugador por su id
    public Optional<Player> findPlayerById(Integer id) {
        return  playerRepository.findById(id);
    }

    //Elimina Player
    public void delete(Player player) {
    	playerRepository.delete(player);
    }

    //Encuentra la lista de cartas de habilidad asociadas a un Player
    public List<AbilityCardInGame> showHand(Player p){
        return p.getAbilityHand(); 
    }

    //Puja de cartas para elección de lider
    public List<AbilityCardInGame> pujarCarta(Player p, int cardId){
        List<AbilityCardInGame> cartasAPujar = p.getCartasPuja();

        for(AbilityCardInGame card : p.getAbilityHand()){
            if(p.getCartasPuja().size() < 2){
                if(card.getId() == cardId){
                    cartasAPujar.add(card);
                    
                }
            }else{

                // ? El usuario ya ha pujado sus 2 cartas por lo que ya no puede pujar mas
            }
        }

        p.setCartasPuja(cartasAPujar);
        List<AbilityCardInGame> cartasABorrar = p.getAbilityHand();
        cartasABorrar.removeAll(cartasAPujar);
        p.setAbilityHand(cartasABorrar);
        savePlayer(p);

        return cartasAPujar;

    }


    public List<Player> findPlayersInGame() {
        return playerRepository.findPlayingPlayers();
    } 



}
