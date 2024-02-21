package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.controllers;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.services.PlayerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControllerTest {


    @Test
    public void createPlayer_shouldCreatePlayer_whenNameIsSet() {
        PlayerService playerServiceMock = Mockito.mock(PlayerService.class);
        PlayerDTO playerDTO = new PlayerDTO(1L, "John Doe");
        Mockito.when(playerServiceMock.createPlayer(playerDTO)).thenReturn(playerDTO);
        PlayerController controller = new PlayerController(playerServiceMock);
        ResponseEntity<PlayerDTO> response = controller.createPlayer(playerDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(playerDTO, response.getBody());
        Mockito.verify(playerServiceMock).createPlayer(playerDTO);
    }

    @Test
    public void updatePlayerName_shouldUpdateName_whenPlayerExists() {
        PlayerService playerServiceMock = Mockito.mock(PlayerService.class);
        Long playerId = 1L;
        String newName = "New Name";
        PlayerDTO updatedPlayerDTO = new PlayerDTO(playerId, newName);
        Mockito.when(playerServiceMock.updatePlayerName(playerId, newName)).thenReturn(updatedPlayerDTO);
        PlayerController controller = new PlayerController(playerServiceMock);
        ResponseEntity<PlayerDTO> response = controller.updatePlayerName(playerId, newName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedPlayerDTO, response.getBody());
        Mockito.verify(playerServiceMock).updatePlayerName(playerId, newName);
    }

    @Test
    public void addGame_shouldAddGame_whenPlayerExists() {
        PlayerService playerServiceMock = Mockito.mock(PlayerService.class);
        Long playerId = 1L;
        GameDTO gameDTO = new GameDTO();
        Mockito.doNothing().when(playerServiceMock).addGame(playerId, gameDTO);
        PlayerController controller = new PlayerController(playerServiceMock);
        ResponseEntity<String> response = controller.addGame(playerId, gameDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Game added successfully for Player ID:" + playerId, response.getBody());
        Mockito.verify(playerServiceMock).addGame(playerId, gameDTO);
    }

    @Test
    public void deleteGames_shouldDeleteGames_whenPlayerExists() {
        PlayerService playerServiceMock = Mockito.mock(PlayerService.class);
        Long playerId = 1L;
        Mockito.doNothing().when(playerServiceMock).deleteGames(playerId);
        PlayerController controller = new PlayerController(playerServiceMock);
        ResponseEntity<String> response = controller.deleteGames(playerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Games deleted successfully for Player ID:" + playerId, response.getBody());
        Mockito.verify(playerServiceMock).deleteGames(playerId);
    }


    @Test
        public void getAllPlayersWithAvgSuccessRate_shouldReturnAllPlayersWithAvgSuccessRate() {
            PlayerService playerServiceMock = Mockito.mock(PlayerService.class);
            List<PlayerDTO> expectedPlayerList = Arrays.asList(
                    new PlayerDTO(1L, "Player 1", new Date(),0.5),
                    new PlayerDTO(2L, "Player 2",new Date(), 0.75));

            Mockito.when(playerServiceMock.getAllPlayersWithAvgSuccessRate()).thenReturn(expectedPlayerList);
            PlayerController controller = new PlayerController(playerServiceMock);
            ResponseEntity<List<PlayerDTO>> response = controller.getAllPlayersWithAvgSuccessRate();

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(expectedPlayerList, response.getBody());
            Mockito.verify(playerServiceMock).getAllPlayersWithAvgSuccessRate();
        }

    @Test
    public void getGamesByPlayerId_shouldReturnGamesForPlayer_whenPlayerExists() {
        PlayerService playerServiceMock = Mockito.mock(PlayerService.class);
        Long playerId = 1L;
        List<GameDTO> expectedGamesList = Arrays.asList(
                new GameDTO("1",1L, 5,3, false),
                new GameDTO("2",1L, 5,2, true)
        );
        Mockito.when(playerServiceMock.getGamesByPlayerId(playerId)).thenReturn(expectedGamesList);
        PlayerController controller = new PlayerController(playerServiceMock);
        ResponseEntity<List<GameDTO>> response = controller.getGamesByPlayerId(playerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedGamesList, response.getBody());
        Mockito.verify(playerServiceMock).getGamesByPlayerId(playerId);
    }

    @Test
    public void getAverageSuccessRate_shouldReturnAverageSuccessRate() {
        PlayerService playerServiceMock = Mockito.mock(PlayerService.class);
        Double expectedAverage = 0.65;
        Mockito.when(playerServiceMock.getAverageSuccessRate()).thenReturn(expectedAverage);
        PlayerController controller = new PlayerController(playerServiceMock);
        ResponseEntity<Double> response = controller.getAverageSuccessRate();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedAverage, response.getBody());
        Mockito.verify(playerServiceMock).getAverageSuccessRate();
    }

    @Test
    public void getLoserPlayer_shouldReturnLoserPlayer() {
        PlayerService playerServiceMock = Mockito.mock(PlayerService.class);
        PlayerDTO expectedLoserPlayer = new PlayerDTO(1L, "Loser Player",new Date(), 0.25);
        Mockito.when(playerServiceMock.getLoserPlayer()).thenReturn(expectedLoserPlayer);
        PlayerController controller = new PlayerController(playerServiceMock);
        ResponseEntity<PlayerDTO> response = controller.getLoserPlayer();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedLoserPlayer, response.getBody());
        Mockito.verify(playerServiceMock).getLoserPlayer();
    }

    @Test
    public void getWinnerPlayer_shouldReturnWinnerPlayer() {
        PlayerService playerServiceMock = Mockito.mock(PlayerService.class);
        PlayerDTO expectedWinnerPlayer = new PlayerDTO(1L, "Winner Player",new Date(), 0.75);
        Mockito.when(playerServiceMock.getWinnerPlayer()).thenReturn(expectedWinnerPlayer);
        PlayerController controller = new PlayerController(playerServiceMock);
        ResponseEntity<PlayerDTO> response = controller.getWinnerPlayer();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedWinnerPlayer, response.getBody());
        Mockito.verify(playerServiceMock).getWinnerPlayer();
    }
}