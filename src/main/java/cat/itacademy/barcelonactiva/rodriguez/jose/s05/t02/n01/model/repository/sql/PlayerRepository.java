package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.repository.sql;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.sql.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {}