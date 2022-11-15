package org.springframework.samples.notimeforheroes.player;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlayerService {
    
    private PlayerRepository playerRepository;

    public Optional<Player> findPlayerById(Integer id) {
        return  playerRepository.findById(id);
    }
}
