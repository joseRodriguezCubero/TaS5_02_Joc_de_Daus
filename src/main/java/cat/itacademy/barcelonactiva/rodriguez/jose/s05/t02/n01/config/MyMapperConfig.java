package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.config;


import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public class MyMapperConfig {
}