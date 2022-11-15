package org.springframework.samples.notimeforheroes.player;


import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    

    private PlayerRepository repo;

    @Autowired
    public PlayerService(PlayerRepository repository){
        this.repo = repository;
    }

    public Collection<Player> findPlayers(){
        return repo.findAll();
    }


    @Transactional
    public void savePlayer(Player player){
        repo.save(player);

    }
    public Optional<Player> findPlayerById(Integer id) {
        return  playerRepository.findById(id);
    }
}
