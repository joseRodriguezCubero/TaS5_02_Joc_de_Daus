package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.mapper;


import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.config.MyMapperConfig;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.entity.sql.Player;
import org.mapstruct.Mapper;

@Mapper(config = MyMapperConfig.class)
public interface PlayerMapper {

    PlayerDTO toDTO(Player player);

    Player toEntity(PlayerDTO playerDTO);

    PlayerDTO toDTOWithAvgSuccessRate(Player player);

}