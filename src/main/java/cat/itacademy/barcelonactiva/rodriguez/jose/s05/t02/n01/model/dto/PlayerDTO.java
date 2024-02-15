package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class PlayerDTO {

    private Long id;

    private String name;

    private Date registrationDate;

    private List<GameDTO> games;

    // ... getters and setters
}