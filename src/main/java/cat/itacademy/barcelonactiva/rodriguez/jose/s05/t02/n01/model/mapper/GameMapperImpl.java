package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.mapper;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.mongo.Game;
import org.springframework.stereotype.Service;

@Service
public class GameMapperImpl implements GameMapper {

    @Override
    public GameDTO toDTO(Game game) {
        GameDTO dto = new GameDTO();
        dto.setId(game.getId());
        dto.setPlayerId(game.getPlayerId());
        dto.setDiceValue1(game.getDiceValue1());
        dto.setDiceValue2(game.getDiceValue2());
        dto.setWon(game.getWon());
        return dto;
    }

    @Override
    public Game toEntity(GameDTO gameDTO) {
        Game game = new Game();
        game.setPlayerId(gameDTO.getPlayerId());
        game.setDiceValue1(gameDTO.getDiceValue1());
        game.setDiceValue2(gameDTO.getDiceValue2());
        game.setWon(gameDTO.isWin());
        return game;
    }

}
