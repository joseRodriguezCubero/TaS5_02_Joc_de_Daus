package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.repository.sql;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.sql.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PlayerRepositoryUnitTest {

    @Autowired
    private PlayerRepository playerRepository;

    private Player johnDoe;

    @BeforeEach
    public void setup() {
        johnDoe = new Player(1L, "john.doe@example.com",new Date());
        playerRepository.save(johnDoe);
    }


    @Test
    public void save_shouldPersistNewPlayer() {
        Player newPlayer = new Player(2L, "jane.doe@example.com",new Date());

        playerRepository.save(newPlayer);

        Optional<Player> savedPlayer = playerRepository.findById(newPlayer.getId());

        assertTrue(savedPlayer.isPresent());
        assertEquals(newPlayer.getId(), savedPlayer.get().getId());
    }

    @Test
    public void delete_shouldRemovePlayer() {
        playerRepository.delete(johnDoe);

        Optional<Player> deletedPlayer = playerRepository.findById(johnDoe.getId());

        assertFalse(deletedPlayer.isPresent());
    }
}