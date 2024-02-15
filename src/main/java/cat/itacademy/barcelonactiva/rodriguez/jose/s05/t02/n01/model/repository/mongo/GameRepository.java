package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.repository.mongo;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.mongo.Game;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface GameRepository extends MongoRepository<Game, Long> {}