package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GameDTO {

    private String id;
    private Long playerId;
    private Integer diceValue1;
    private Integer diceValue2;
    private Boolean won;

//TODO mirar de no duplicar el campo won, intentar alejarlo de la entidad. Solo en DTO

    public GameDTO() {
    }
    public boolean isWin(){
        return this.won = this.diceValue1 + this.diceValue2 == 7;
    }


    @Override
    public String toString() {
        return "GameDTO{" +
                "id='" + id + '\'' +
                ", playerId=" + playerId +
                ", diceValue1=" + diceValue1 +
                ", diceValue2=" + diceValue2 +
                ", won=" + won +
                '}';
    }
}