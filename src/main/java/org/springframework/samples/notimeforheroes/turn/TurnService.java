package org.springframework.samples.notimeforheroes.turn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.game.GameRepository;
import org.springframework.samples.notimeforheroes.player.Player;
import org.springframework.stereotype.Service;

@Service
public class TurnService {
    
    private final TurnRepository repo;
    private final GameRepository gameRepository;

    @Autowired
    public TurnService(TurnRepository turnRepo, GameRepository gameRepo){
        this.gameRepository = gameRepo;
        this.repo = turnRepo;
    };

    public List<Player> setLeader(int gameId){
        List<Player> players = gameRepository.findPlayersInGame(gameId);

        repo.findById(1).get().getPlayer();



        return players;


    }

    public void newTurn(Game game, Player player, PhaseType phaseType){
        Turn turn = new Turn();
        turn.setGame(game);
        turn.setPlayer(player);
        turn.setType(phaseType);
        save(turn);

    }

    public void save(Turn turn){
        repo.save(turn);
    }

}
