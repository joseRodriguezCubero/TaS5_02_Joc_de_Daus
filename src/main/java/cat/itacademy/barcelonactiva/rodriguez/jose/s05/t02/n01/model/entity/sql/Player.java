package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.sql;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Builder
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

    public Player(Long id, String name) {
        this.id = id;
        this.name = name;
        this.registrationDate = new Date();
    }
}