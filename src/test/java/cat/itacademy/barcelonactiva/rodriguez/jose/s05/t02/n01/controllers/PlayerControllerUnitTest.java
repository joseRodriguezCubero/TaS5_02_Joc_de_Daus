package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.controllers;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.mapper.PlayerMapper;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.repository.mongo.GameRepository;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.repository.sql.PlayerRepository;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.services.JwtService;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.services.PlayerService;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(
        controllers = PlayerController.class,
        excludeAutoConfiguration = {
                UserDetailsServiceAutoConfiguration.class, SecurityAutoConfiguration.class
        }
)
public class PlayerControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserService userService;

    @MockBean
    private PlayerRepository playerRepository;

    @MockBean
    private GameRepository gameRepository;

    @MockBean
    private PlayerMapper playerMapper;

    private PlayerDTO player1;
    private PlayerDTO player2;

    @BeforeEach
    void setUp() {
        player1 = new PlayerDTO(1L, "Player1", new Date(), 70.0);
        player2 = new PlayerDTO(2L, "Player2", new Date(), 80.0);
    }


    @DisplayName("PlayerControllerTest - Test return all players with average success rate")
    @Test
    void should_return_all_players_with_avg_success_rate() throws Exception {
        when(playerService.getAllPlayersWithAvgSuccessRate()).thenReturn(Arrays.asList(player1, player2));
        mockMvc.perform(get("/api/v1/players"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Player1"))
                .andExpect(jsonPath("$[1].name").value("Player2"));
    }

    @DisplayName("PlayerControllerTest - Test add new game for player")
    @Test
    void should_add_new_game_for_player() throws Exception {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setPlayerId(1L);
        gameDTO.setDiceValue1(4);
        gameDTO.setDiceValue2(3);
        gameDTO.setWon(true);

        mockMvc.perform(post("/api/v1/players/1/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(gameDTO))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Game added successfully for Player ID:1"));
    }

    @DisplayName("PlayerControllerTest - Test delete games for player")
    @Test
    void should_delete_games_for_player() throws Exception {
        mockMvc.perform(delete("/api/v1/players/1/games"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Games deleted successfully for Player ID:1"));
    }

    @DisplayName("PlayerControllerTest - Test get games by player id")
    @Test
    void should_get_games_by_player_id() throws Exception {
        GameDTO game1 = new GameDTO("1", 1L, 4, 3, true);
        GameDTO game2 = new GameDTO("2", 1L, 6, 1, true);
        when(playerService.getGamesByPlayerId(1L)).thenReturn(Arrays.asList(game1, game2));

        mockMvc.perform(get("/api/v1/players/1/games"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].diceValue1").value(4))
                .andExpect(jsonPath("$[1].diceValue1").value(6));
    }

    @DisplayName("PlayerControllerTest - Test get average success rate")
    @Test
    void should_get_average_success_rate() throws Exception {
        when(playerService.getAverageSuccessRate()).thenReturn(75.0);
        mockMvc.perform(get("/api/v1/players/ranking"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(75.0));
    }

    @DisplayName("PlayerControllerTest - Test get loser player")
    @Test
    @WithMockUser(username = "user")
    void should_get_loser_player() throws Exception {
        when(playerService.getLoserPlayer()).thenReturn(player1);
        mockMvc.perform(get("/api/v1/players/ranking/loser"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Player1"));
    }

    @DisplayName("PlayerControllerTest - Test get winner player")
    @Test
    @WithMockUser(username = "user")
    void should_get_winner_player() throws Exception {
        when(playerService.getWinnerPlayer()).thenReturn(player2);
        mockMvc.perform(get("/api/v1/players/ranking/winner"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Player2"));
    }
}