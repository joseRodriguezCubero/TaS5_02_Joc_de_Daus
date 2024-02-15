package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GameDTO {

    private Long playerId;

    private Long id;

    private String playerName;

    private Integer diceValue1;

    private Integer diceValue2;

    private Boolean won;



}