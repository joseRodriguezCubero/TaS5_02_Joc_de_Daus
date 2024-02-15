package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.services;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.mongo.Game;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.sql.Player;

public class Mapper {

    public static Player toPlayer(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setId(playerDTO.getId());
        player.setName(playerDTO.getName());
        player.setRegistrationDate(playerDTO.getRegistrationDate());
        return player;
    }

    public static PlayerDTO toPlayerDTO(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getName());
        playerDTO.setRegistrationDate(player.getRegistrationDate());
        return playerDTO;
    }

    public static Game toGame(GameDTO gameDTO) {
        Game game = new Game();
        game.setId(gameDTO.getId());
        game.setPlayerId(gameDTO.getPlayerId());
        game.setDiceValue1(gameDTO.getDiceValue1());
        game.setDiceValue2(gameDTO.getDiceValue2());
        game.setWon(gameDTO.getWon());
        return game;
    }

    public static GameDTO toGameDTO(Game game) {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(game.getPlayerId());
        gameDTO.setDiceValue1(game.getDiceValue1());
        gameDTO.setDiceValue2(game.getDiceValue2());
        gameDTO.setWon(game.getWon());
        return gameDTO;
    }
}