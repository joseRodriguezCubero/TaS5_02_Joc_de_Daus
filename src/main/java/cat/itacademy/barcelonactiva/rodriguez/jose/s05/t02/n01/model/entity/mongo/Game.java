package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.mongo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document(collection = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long playerId;

    private Integer diceValue1;

    private Integer diceValue2;

    private Boolean won;

    public Game(Long playerId, Integer diceValue1, Integer diceValue2) {
        this.playerId = playerId;
        this.diceValue1 = diceValue1;
        this.diceValue2 = diceValue2;
        this.won = diceValue1 + diceValue2 == 7;
    }

    public Game() {

    }
}