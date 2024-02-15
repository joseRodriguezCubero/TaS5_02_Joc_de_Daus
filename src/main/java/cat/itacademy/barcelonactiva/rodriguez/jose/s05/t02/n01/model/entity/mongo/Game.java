package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.mongo;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.sql.PlayerSql;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Entity
@Document(collection = "games")
public class GameMongo {

    @Id
    private String id;



    @Getter
    @Setter
    private List<Integer> diceValues;

    @Getter
    @Setter
    private boolean won;

    public GameMongo(String id, List<Integer> diceValues, boolean won) {
        this.id = id;
        this.diceValues = diceValues;
        this.won = won;
    }
}