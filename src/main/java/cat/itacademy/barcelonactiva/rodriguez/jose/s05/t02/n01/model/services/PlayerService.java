package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.services;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.PlayerDTO;

import java.util.List;

public interface PlayerService {

    PlayerDTO createPlayer(PlayerDTO playerDTO);

    PlayerDTO updatePlayerName(Long id, String name);

    void addGame(Long playerId, GameDTO gameDTO);

    void deleteGames(Long playerId);

    List<PlayerDTO> getAllPlayersWithAvgSuccessRate();

    List<GameDTO> getGamesByPlayerId(Long playerId);

    Double getAverageSuccessRate();

    PlayerDTO getLoserPlayer();

    PlayerDTO getWinnerPlayer();

}