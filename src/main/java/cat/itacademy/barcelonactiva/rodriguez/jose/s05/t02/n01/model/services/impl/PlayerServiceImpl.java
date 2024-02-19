package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.services.impl;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.mongo.Game;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.sql.Player;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.exceptions.GamesNotFoundException;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.exceptions.PlayerAlreadyExistException;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.exceptions.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.mapper.GameMapper;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.mapper.PlayerMapper;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.repository.mongo.GameRepository;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.repository.sql.PlayerRepository;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.services.PlayerService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    private final GameRepository gameRepository;

    private final PlayerMapper playerMapper;

    private final GameMapper gameMapper;

    public PlayerServiceImpl(PlayerRepository playerRepository, GameRepository gameRepository, PlayerMapper playerMapper, GameMapper gameMapper) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.playerMapper = playerMapper;
        this.gameMapper = gameMapper;
    }

    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        playerRepository.findByNameIgnoreCase(playerDTO.getName())
                .ifPresent(player -> {
                    throw new PlayerAlreadyExistException("Already exist player with given name:" + playerDTO.getName());
                });
        Player player = playerMapper.toEntity(playerDTO);
        playerRepository.save(player);
        return playerMapper.toDTO(player);
    }

    @Override
    public PlayerDTO updatePlayerName(Long id, String name) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException("Player Not Found with ID: " + id));
        if (player == null) {
            throw new IllegalArgumentException("player ID cannot be null");
        }
        player.setName(name);
        playerRepository.save(player);
        return playerMapper.toDTO(player);
    }


    @Override
    public void addGame(Long playerId, GameDTO gameDTO) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException("Player Not Found with ID: " + playerId));
        if (player == null) {
            throw new IllegalArgumentException("player ID cannot be null");
        }
            Game game = gameMapper.toEntity(gameDTO);
            game.setPlayerId(player.getId());
            gameRepository.save(game);
    }

    @Override
    public void deleteGames(Long playerId) {
        try {
            List<Game> games = gameRepository.findByPlayerId(playerId);
            gameRepository.deleteAll(games);
        } catch (EmptyResultDataAccessException e) {
            throw new GamesNotFoundException("No games found for player with ID " + playerId);
        }
    }

    @Override
    public List<PlayerDTO> getAllPlayersWithAvgSuccessRate() {
        List<Player> players = playerRepository.findAll();

        return players.stream()
                .map(player -> {
                    List<Game> games = gameRepository.findByPlayerId(player.getId());

                    long totalGames = games.size();
                    long wonGames = games.stream().filter(Game::getWon).count();

                    PlayerDTO playerDTO = playerMapper.toDTO(player);
                    playerDTO.setAvgSuccessRate((double) wonGames / totalGames * 100);

                    return playerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<GameDTO> getGamesByPlayerId(Long playerId) {
        try {
            List<Game> games = gameRepository.findByPlayerId(playerId);

            List<GameDTO> gameDTOs = games.stream()
                    .map(gameMapper::toDTO)
                    .collect(Collectors.toList());
            return gameDTOs;
        } catch (EmptyResultDataAccessException e) {
            throw new GamesNotFoundException("No games found for player with ID " + playerId);
        }
    }
    @Override
    public Double getAverageSuccessRate() {
        List<Game> allGames = gameRepository.findAll();
        long totalGames = allGames.size();
        long wonGames = allGames.stream().filter(Game::getWon).count();
        return (double) wonGames / totalGames * 100;
    }

    @Override
    public PlayerDTO getLoserPlayer() {
        List<PlayerDTO> playersWithAvgSuccessRate = getAllPlayersWithAvgSuccessRate();

        return playersWithAvgSuccessRate.stream()
                .min(Comparator.comparingDouble(PlayerDTO::getAvgSuccessRate))
                .orElse(null);
    }

    @Override
    public PlayerDTO getWinnerPlayer() {
        List<PlayerDTO> playersWithAvgSuccessRate = getAllPlayersWithAvgSuccessRate();

        return playersWithAvgSuccessRate.stream()
                .max(Comparator.comparingDouble(PlayerDTO::getAvgSuccessRate))
                .orElse(null);
    }

}
