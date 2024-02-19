package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.sql;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    public Player() {
        this.registrationDate = new Date();
    }

}