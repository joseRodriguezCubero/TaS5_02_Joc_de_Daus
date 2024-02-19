package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class GameDTO {

    private Long id;
    private Long playerId; // Reference to the player (Long instead of Player reference)
    private Integer diceValue1;
    private Integer diceValue2;
    private Boolean won;

    public GameDTO() {
    }

    public GameDTO(Long id, Long playerId, Integer diceValue1, Integer diceValue2) {
        this.id = id;
        this.playerId = playerId;
        this.diceValue1 = diceValue1;
        this.diceValue2 = diceValue2;
        this.won = diceValue1 + diceValue2 == 7;
    }
}