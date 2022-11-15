package org.springframework.samples.notimeforheroes.player;


import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public Collection<Player> findPlayers(){
        return playerRepository.findAll();
    }


    @Transactional
    public void savePlayer(Player player){

    	playerRepository.save(player);


    }
    public Optional<Player> findPlayerById(Integer id) {
        return  playerRepository.findById(id);
    }
}
