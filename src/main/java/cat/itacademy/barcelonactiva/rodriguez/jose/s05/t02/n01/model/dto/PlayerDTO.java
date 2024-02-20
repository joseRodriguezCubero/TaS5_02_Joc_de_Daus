package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class PlayerDTO {

    private Long id;
    private String name;
    private Date registrationDate;
    private Double avgSuccessRate;

    public PlayerDTO() {
    }

    public PlayerDTO(Long id, String name, Date registrationDate, Double avgSuccessRate) {
        this.id = id;
        this.name = name;
        this.registrationDate = registrationDate;
        this.avgSuccessRate = avgSuccessRate;
    }



    @Override
    public String toString() {
        return "PlayerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationDate=" + registrationDate +
                ", avgSuccessRate=" + avgSuccessRate +
                '}';
    }
}