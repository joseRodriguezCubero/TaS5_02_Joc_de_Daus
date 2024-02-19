package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class PlayerDTO {

    private Long id;
    private String name;
    private Date registrationDate;
    private Double avgSuccessRate; // Add this field for `getAllPlayersWithAvgSuccessRate`

    public PlayerDTO() {
    }

    public PlayerDTO(Long id, String name, Date registrationDate, Double avgSuccessRate) {
        this.id = id;
        this.name = name;
        this.registrationDate = registrationDate;
        this.avgSuccessRate = avgSuccessRate;
    }
}