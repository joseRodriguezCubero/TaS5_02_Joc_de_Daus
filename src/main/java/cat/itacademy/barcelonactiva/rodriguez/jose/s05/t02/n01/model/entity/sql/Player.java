package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.sql;


import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.mongo.Game;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Entity
public class PlayerSql {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String name;
    @Setter
    private Date registrationDate;
    @Setter
    @OneToMany(mappedBy = "player")
    private List<Game> games;


}
