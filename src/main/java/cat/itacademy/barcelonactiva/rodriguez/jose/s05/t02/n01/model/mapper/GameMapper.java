package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.mapper;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.config.MyMapperConfig;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.mongo.Game;
import org.mapstruct.Mapper;

@Mapper(config = MyMapperConfig.class)
public interface GameMapper {

    GameDTO toDTO(Game game);

    Game toEntity(GameDTO gameDTO);

}