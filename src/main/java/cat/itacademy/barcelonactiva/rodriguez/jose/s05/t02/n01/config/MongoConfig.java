package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.repository.mongo")
public class MongoConfig {
}