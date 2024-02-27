package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.mapper;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.mongo.Game;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.sql.Player;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.repository.mongo.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerMapperImpl implements PlayerMapper {

    private final GameRepository gameRepository;

    public PlayerMapperImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public PlayerDTO toDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setId(player.getId());
        dto.setName(player.getName());
        dto.setRegistrationDate(player.getRegistrationDate());
        return dto;
    }

    @Override
    public Player toEntity(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setName(playerDTO.getName());
        return player;
    }

    public PlayerDTO toDTOWithAvgSuccessRate(Player player) {
        PlayerDTO dto = toDTO(player);
        Double avgSuccessRate = calculateAvgSuccessRate(player);
        dto.setAvgSuccessRate(avgSuccessRate);
        return dto;
    }

    private Double calculateAvgSuccessRate(Player player) {
        List<Game> games = gameRepository.findByPlayerId(player.getId());
        long totalGames = games.size();
        long wonGames = games.stream()
                .filter(game -> game.getDiceValue1() + game.getDiceValue2() == 7)
                .count();
        return (double) wonGames / totalGames;
    }

}