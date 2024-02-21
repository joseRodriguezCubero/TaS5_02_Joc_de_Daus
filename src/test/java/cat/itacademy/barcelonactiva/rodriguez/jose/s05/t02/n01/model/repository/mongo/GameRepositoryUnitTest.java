package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.repository.mongo;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.mongo.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class GameRepositoryUnitTest {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private MongoTemplate mongoTemplate;  // For direct MongoDB operations

    @DisplayName("GameRepositoryUnitTest - Test return games list")
    @Test
    void findAll_should_return_games_list() {
        List<Game> gamesList = gameRepository.findAll();
        // Adjust expected size based on your initial data
        assertEquals(4, gamesList.size());
    }

    @DisplayName("GameRepositoryUnitTest - Test find games by player ID")
    @Test
    void findByPlayerId_should_return_games_list() {
        List<Game> games = gameRepository.findByPlayerId(1L);
        assertEquals(1, games.size()); // Assuming 2 games for player 1
    }

    @DisplayName("GameRepositoryUnitTest - Test no games found for player ID")
    @Test
    void findByPlayerId_no_games_found() {
        List<Game> games = gameRepository.findByPlayerId(4L);
        assertTrue(games.isEmpty());
    }

    @DisplayName("GameRepositoryUnitTest - Test for insert new game")
    @Test
    void save_should_insert_new_game() {
        Game saved = new Game("1", 1L, 10, 5, true);
        Game returnedGame = gameRepository.save(saved);
        assertNotNull(returnedGame);
        assertNotNull(returnedGame.getId());
        assertEquals(saved.getPlayerId(), returnedGame.getPlayerId());
        assertEquals(saved.getDiceValue1(), returnedGame.getDiceValue1());
        assertEquals(saved.getDiceValue2(), returnedGame.getDiceValue2());
        assertEquals(saved.getWon(), returnedGame.getWon());
    }

}