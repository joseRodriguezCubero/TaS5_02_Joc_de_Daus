package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.repository.sql")
public class JpaConfig {
}