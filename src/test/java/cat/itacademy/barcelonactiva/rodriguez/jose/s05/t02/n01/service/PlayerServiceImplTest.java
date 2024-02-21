package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.service;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.mongo.Game;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.sql.Player;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.exceptions.GamesNotFoundException;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.exceptions.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.mapper.GameMapper;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.mapper.PlayerMapper;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.repository.mongo.GameRepository;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.repository.sql.PlayerRepository;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.services.impl.PlayerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlayerMapper playerMapper;

    @Mock
    private GameMapper gameMapper;

    @InjectMocks
    private PlayerServiceImpl playerService;

    private static final Long PLAYER_ID_1 = 1L;
    private static final String PLAYER_NAME_1 = "Jugador 1";
    private static final String NEW_PLAYER_NAME = "Nuevo nombre";

    private Player player1;
    private PlayerDTO playerDTO1;
    private Game game1;
    private GameDTO gameDTO1;

    @BeforeEach
    void setUp() {
        player1 = new Player(PLAYER_ID_1, PLAYER_NAME_1);
        playerDTO1 = new PlayerDTO(PLAYER_ID_1, PLAYER_NAME_1);
        game1 = new Game("1", PLAYER_ID_1, 1, 2, false);
        gameDTO1 = new GameDTO("1", PLAYER_ID_1, 1, 2, false);
    }

    @Test
    void createPlayer_shouldCreatePlayer() {
        when(playerRepository.findByNameIgnoreCase(PLAYER_NAME_1)).thenReturn(Optional.empty());
        when(playerMapper.toEntity(playerDTO1)).thenReturn(player1);
        when(playerRepository.save(player1)).thenReturn(player1);
        when(playerMapper.toDTO(player1)).thenReturn(playerDTO1);

        PlayerDTO createdPlayer = playerService.createPlayer(playerDTO1);

        assertEquals(playerDTO1, createdPlayer);

        verify(playerRepository).findByNameIgnoreCase(PLAYER_NAME_1);
        verify(playerMapper).toEntity(playerDTO1);
        verify(playerRepository).save(player1);
        verify(playerMapper).toDTO(player1);
    }


    @Test
    void updatePlayerName_shouldThrowPlayerNotFoundException() {
        when(playerRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(PlayerNotFoundException.class, () -> playerService.updatePlayerName(100L, NEW_PLAYER_NAME));

        verify(playerRepository).findById(anyLong());
    }

    @Test
    void addGame_shouldAddGame() {
        when(playerRepository.findById(PLAYER_ID_1)).thenReturn(Optional.of(player1));
        when(gameMapper.toEntity(gameDTO1)).thenReturn(game1);
        when(gameRepository.save(game1)).thenReturn(game1);

        playerService.addGame(PLAYER_ID_1, gameDTO1);

        verify(playerRepository).findById(PLAYER_ID_1);
        verify(gameMapper).toEntity(gameDTO1);
        verify(gameRepository).save(game1);
    }

    @Test
    void addGame_shouldThrowPlayerNotFoundException() {
        when(playerRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(PlayerNotFoundException.class, () -> playerService.addGame(100L, gameDTO1));

        verify(playerRepository).findById(anyLong());
    }

}